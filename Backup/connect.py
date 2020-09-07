import mysql.connector
from mysql.connector import Error
from time import sleep

def try_conn():
    conn=True
    while conn:
        try:
            connection = mysql.connector.connect(
                    host='localhost',
                    port= 33060,
                    user='root',
                    password='password',
                    database='fleet'
                    )
        except Error as error:
            print("--- Not connected --- ")
                
        else:
            conn=False
            return True

try_conn()