import mysql.connector
from mysql.connector import Error
from time import sleep

def try_conn():
    conn=True
    while conn:
        try:
            connection = mysql.connector.connect(
                    host='localhost',
                    port= 3306,
                    user='root',
                    password='password',
                    database='fleet'
                    )
        except Error as error:
            print("--- Not connected --- ")
            sleep(1)    
        else:
            print("--- Connected!!! ---")
            conn=False
            return True

try_conn()
