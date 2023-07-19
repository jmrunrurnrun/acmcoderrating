import csv
import time
from datetime import datetime

import requests
import json
import mysql.connector
def getUrlText(url):    #根据url获取html资源,返回html文本
    while True:
        try:
            html = requests.get(url)
            html = html.text
            break
        except requests.exceptions.ConnectionError:
            print('ConnectionError -- please wait 3 seconds')
            time.sleep(3)
        except requests.exceptions.ChunkedEncodingError:
            print('ChunkedEncodingError -- please wait 3 seconds')
            time.sleep(3)
        except:
            print('Unfortunitely -- An Unknow Error Happened, Please wait 3 seconds')
            time.sleep(3)
    return html


# codeforces get one user's data
def getCFUserData(cfID):    #根据cfID获取比赛记录,返回一个字典列表,里面存储此学生的比赛记录
    url = "https://codeforces.com/api/user.rating?handle=" + cfID.strip()
    html = getUrlText(url)
    js = json.loads(html)
    if 'result' not in js.keys():
        return []
    results = json.loads(html)['result']
    if len(results) == 0:
        return []
    datalist = []
    d = results[-1]
    date = d["ratingUpdateTimeSeconds"]
    date = time.strftime("%Y-%m-%d %H:%M:%S",time.localtime(int(date)))
    contestID = d["contestId"]
    contest = d["contestName"]
    rank = d["rank"]
    newRating = d["newRating"]
    oldRating = d['oldRating']
    sum = len(results)
    datalist.append({
        'id': cfID,
        'contest': contest,
        'contestID': contestID,
        'rank': rank,
        'oldRating': oldRating,
        'newRating': newRating,
        'sumContest': sum,
        'date': date
        })
    return datalist



if __name__ == "__main__":
    idlist = ['1710252529', 'buctAI2001lipeifeng','BUCTTSH','Tlopex','zpf666','liangdaye','empathy117','disloss','Vanthoci','buctAI2001lipeifeng',
'LetMeFly',
'xushuyu-s-grandfather',
'waiiting',
'xiaopengyou',
'code__learner',
'CleverXun',
'oneq',
'Victoria_king',
'ssjhgg',
'Shangguanyunxiao',
'111',
'littlelittlesix',
'wps0807',
'Rayanx',
'langa',
'Zhengliangfei',
'FanWQ',
'bob118',
'sunsunsun',
'ymyyy',
'Ovo_275',
'nyaaaa',
'Gowilli',
'xyy_liangcha',
'xiojoy',
'1710252529',
'Arickkaa',
'KillyMolisian',
'Carpe_diem_',
'longcheng_x',
'Hunter_ccc',
'rayadwind',
'xcvbn',
'chhb',
'ssssssya',
'NanBoerWan',
'XSQ',
'LancerLas',
'Buctpeng',
'2021040086',
'bcccccc',
'happen',
'kukuZe',
'yzzupup']
    result = []
    for id in idlist:
        ans = getCFUserData(id)
        result = result + ans
    
    db = mysql.connector.connect(
        host="localhost",
        user="root",
        password="123456",
        database="acm"
    )
    cursor = db.cursor()

    # 创建codeforces表
    create_table_query = """
        CREATE TABLE IF NOT EXISTS codeforces (
            cf_id VARCHAR(255),
            cf_contest VARCHAR(255),
            cf_contest_id INT,
            cf_rank INT,
            cf_old_rating INT,
            cf_new_rating INT,
            cf_sum_contest INT,
            cf_date DATETIME
        )
    """
    cursor.execute(create_table_query)
    db.commit()

    # 插入数据到codeforces表
for item in result:
    insert_query = """
        INSERT INTO codeforces (cf_id, cf_contest, cf_contest_id, cf_rank, cf_old_rating, cf_new_rating, cf_sum_contest, cf_date)
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s)
        ON DUPLICATE KEY UPDATE
            cf_contest = VALUES(cf_contest),
            cf_contest_id = VALUES(cf_contest_id),
            cf_rank = VALUES(cf_rank),
            cf_old_rating = VALUES(cf_old_rating),
            cf_new_rating = VALUES(cf_new_rating),
            cf_sum_contest = VALUES(cf_sum_contest),
            cf_date = VALUES(cf_date)
    """
    values = (
        item['id'],
        item['contest'],
        item['contestID'],
        item['rank'],
        item['oldRating'],
        item['newRating'],
        item['sumContest'],
        item['date']
    )
    cursor.execute(insert_query, values)
    db.commit()