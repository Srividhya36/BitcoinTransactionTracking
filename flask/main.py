import pymysql
from redis import StrictRedis
from app import app
from db_config import mysql
from flask import jsonify
from flask import flash, request

	

@app.route('/show_transactions')
def show_transactions():
	try:
		conn = mysql.connect()
		cursor = conn.cursor(pymysql.cursors.DictCursor)
		cursor.execute("SELECT * FROM kafkadata")
		rows = cursor.fetchall()
		print(rows)
		type(rows)
		resp = jsonify(rows)
		resp.status_code = 200
		return resp
	except Exception as e:
		print(e)
	finally:
		cursor.close() 
		conn.close()

@app.route('/high_value_addr')
def high_value_addr():
	try:
		conn = mysql.connect()
		cursor = conn.cursor(pymysql.cursors.DictCursor)
		cursor.execute("SELECT max(amount),hash from kafkadata GROUP BY hash;")
		rows = cursor.fetchall()
		resp = jsonify(rows)
		resp.status_code = 200
		return resp
	except Exception as e:
		print(e)
	finally:
		cursor.close() 
		conn.close()


@app.route('/transactions_count_per_minute')
def transactions_count_per_minute():
	try:
		redis_db = StrictRedis(host="localhost", port=6379,db=0, decode_responses=True)
		keys = redis_db.keys()
		values=redis_db.mget(keys)
		print(values)	
		resp = jsonify(values)
		resp.status_code = 200
		return resp
	except Exception as e:
		print(e)
	finally:
		redis_db.close()

		

@app.errorhandler(404)
def not_found(error=None):
    message = {
        'status': 404,
        'message': 'Not Found: ' + request.url,
    }
    resp = jsonify(message)
    resp.status_code = 404

    return resp
		
if __name__ == "__main__":
    app.run()


