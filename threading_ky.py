import re
from urllib.request import urlopen, Request
import pymysql.cursors
import threading,queue, time
import sys
import importlib

# 连接数据库
connect = pymysql.Connect(
    host='39.108.239.129',
    port=3306,
    user='root',
    passwd='root',
    db='springBoot',
    charset='utf8'
)
# 获取游标
cursor = connect.cursor()
sql = "INSERT INTO py_ky_info (`year`, `name`, `class`,`subject_code`, `subject`, `sum_score`,`one`, `two`, `three`, `four`) VALUES ( '%s', '%s','%s','%s', '%s','%s' ,'%s', '%s','%s' ,'%s' )"

start_page = 1 #开始爬取的页数
max_pages = 200 # 从开始页往后爬取的页数

importlib.reload(sys)
_DATA = []
FILE_LOCK = threading.Lock()
SHARE_Q = queue.Queue()  #构造一个不限制大小的的队列
_WORKER_THREAD_NUM = 5  #设置线程的个数

class MyThread(threading.Thread) :

    def __init__(self, func) :
        super(MyThread, self).__init__()  #调用父类的构造函数
        self.func = func  #传入线程函数逻辑

    def run(self) :
        self.func()

def worker() :
    global SHARE_Q
    while not SHARE_Q.empty():
        url = SHARE_Q.get() #获得任务
        my_page = kaoyanSpider.get_page(kaoyanSpider,url)
        kaoyanSpider.find_title(kaoyanSpider,my_page)
        time.sleep(1)
        SHARE_Q.task_done()

class kaoyanSpider(object):
    """类的简要说明

    本类主要用于抓取历年考研录取信息

    Attributes:
        page: 用于表示当前所处的抓取页面
        cur_url: 用于表示当前争取抓取页面的url
        datas: 存储处理好的抓取到的历年考研录取信息
    """
    def __init__(self):
        self.cur_url = "http://www.kaoshidian.com/kaoyan/fs-0-0-0-0-{page}.html"
        print("历年考研录取信息爬虫准备就绪, 准备爬取数据...")

    def arr_size(self,arr, size):
        s = []
        for i in range(0, int(len(arr)) + 1, size):
            c = arr[i:i + size]
            s.append(c)
        return s

    def get_page(self,url):
        """

        根据当前页码爬取网页HTML

        Args:
            cur_page: 表示当前所抓取的网站页码

        Returns:
            返回抓取到整个页面的HTML(unicode编码)

        Raises:
            URLError:url引发的异常
        """
        print(url)
        my_page = ''
        try:
            headers = {
                'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36'}
            ret = Request(url, headers=headers)
            res = urlopen(ret)
            my_page = res.read().decode('utf-8')
        except Exception as error:
            print(error)
        return my_page

    def find_title(self,my_page):
        """

        通过返回的整个网页HTML, 正则匹配所有的历年考研录取信息


        Args:
            my_page: 传入页面的HTML文本用于正则匹配
        """
        title = re.findall(r'<td.*?class="tc">(.*?)</td>', my_page, re.S)
        title = self.arr_size(self,title,10)
        for index, item in enumerate(title):
            for a,data in enumerate(item):
                if a == 1:
                    sn = re.findall(r'<a.*?>([\S\s]*?)</a>', data, re.S)[0]
                    item[a] = sn
                if a == 4:
                    sn = re.findall(r'<a.*?>([\S\s]*?)</a>', data, re.S)[0]
                    item[a] = sn
            if item.__len__() > 0 and item[1] != '' and item[5] != '':
                data = (item[0], item[1], item[2],item[3],item[4],item[5],item[6],item[7],item[8],item[9])
                FILE_LOCK.acquire()
                cursor.execute(sql % data)
                connect.commit()
                FILE_LOCK.release()
                print(data)

    def start_spider(self):
        """

        爬虫入口, 并控制爬虫抓取页面的范围
        """
        global SHARE_Q
        threads = []
        # 向队列中放入任务, 真正使用时, 应该设置为可持续的放入任务
        for index in range(start_page,start_page+max_pages):
            SHARE_Q.put(self.cur_url.format(page=index))
        for i in range(_WORKER_THREAD_NUM):
            thread = MyThread(worker)
            thread.start()  # 线程开始处理任务
            threads.append(thread)
        for thread in threads:
            thread.join()
        SHARE_Q.join()

def main():
    print("""
        ###############################
            一个简单的历年考研录取信息爬虫
            Author: herong22384
            Version: 0.0.1
            Date: 2020-04-18
        ###############################
    """)
    my_spider = kaoyanSpider()
    my_spider.start_spider()
    print("历年考研录取信息爬虫爬取结束...")
    # 关闭数据库连接
    connect.close()


if __name__ == '__main__':
    main()









