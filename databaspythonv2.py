import mysql.connector
from mysql.connector import Error
import time

class DB:

    def connect(self):
        try:
            self.conn = mysql.connector.connect(
                    host="127.0.0.1",
                    user="gunnar",
                    password="Linux4Ever",
                    database="fleet"
                    )
        except Error as error:
            print(error)
            self.conn = None
            time.sleep(3)
        return self.conn
    
    def query(self, sql):
        try:
            cursor = self.conn.cursor()
            cursor.execute(sql)
        except Error as error:
            self.connect()
            cursor = self.conn.cursor()
            cursor.execute(sql)
        return cursor

while 1:
    db = DB()
    conn = db.connect()

    if conn:
        sql = "select * from uvshipposition"
        cur = db.query(sql)
        result = cur.fetchall() 
        print(result)
        with open('./pythonhtml.html', 'w') as f:
            f.write(""" <table style='width:100%'> 
                        <tr>
                            <th>Ship name</th>
                            <th>Bearing</th>
                            <th>Current position</th>
                            <th>Nautical milage</th>
                        </tr>""")

        with open('./pythonhtml.html', 'a') as f:
            for row in result:
                f.write(f"<tr><td>{row[0]}</td>\n<td>{row[1]}</td>\n<td>{row[2]}</td>\n<td>{row[3]}</td></tr>")
            f.write('</table>')
        time.sleep(3)





