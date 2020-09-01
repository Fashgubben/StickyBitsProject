import mysql.connector
from mysql.connector import Error
import time


class DB:

    def connect(self):
        try:
            self.conn = mysql.connector.connect(
                    host="docker_mysql-db_1",
                    port= 3306,
                    user="root",
                    password="password",
                    database="fleet"
                    )
        except Error as error:
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
<<<<<<< HEAD
        
        with open('/html/test.html', 'w') as f:
=======
        with open('/code/html/test.html', 'w') as f:
>>>>>>> c2ee5e9c4a4c2db0a592e8bcc50495b7beca42d0
            f.write(""" <!DOCTYPE html>
                        <html>
                        <head>
                        <meta http-equiv="refresh" content="5" />
                        <meta charset="UTF-8">
                        <style>
                        table {
                            font-family: arial, sans-serif;
                            border-collapse: collapse;
                            width: 50%;
                            }
                        
                        td, th {
                            border: 1px solid #dddddd;
                            text-align: left;
                            padding: 8px;
                            }

                        tr:nth-child(even) {
                            background-color: #dddddd;
                            }

                        .gdot {
                            height: 15px;
                            width: 15px;
                            background-color: #7CFC00;
                            border-radius: 50%;
                            display: inline-block;
                            }

                        .rdot {
                            height: 15px;
                            width: 15px;
                            background-color: #FF0000;
                            border-radius: 50%;
                            display: inline-block;
                            }

                        .moveright {
                            text-align: right;
                            }
                
                        </style>
                        </head>
                        <body>
                            <h2>Ship position</h2>
                            
                            <table>
                            <tr>
                                <th>Name</th>
                                <th>Bearing</th>
                                <th>Coorniates</th>
                                <th>Nautical milage</th>
                            <tr>""")

        with open('/code/html/test.html', 'a') as f:
            for row in result:
                f.write(f"<tr><td>{row[0]}</td>\n<td>{row[1]}</td>\n<td>{row[2]}</td>\n<td>{row[3]}</td></tr>")
            f.write("\n</table>\n</body>\n</html>")
        time.sleep(3)





