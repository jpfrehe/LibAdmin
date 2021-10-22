import requests
import concurrent.futures
import threading
import json
from random import randrange

thread_local = threading.local()

def get_session():
    if not hasattr(thread_local, "session"):
        thread_local.session = requests.Session()
    return thread_local.session

def post_verlag_test():
    session = get_session()
    headers = {'Content-Type': 'application/json'}
    book = {'book': {'title':'Die Bibel','author':'Deine Mudda','publisher':'Christlicher Staat deutscher Nation','isbn':'978-3-16-148410-0'}}
    with session.post("http://localhost:8080/addBook", headers=headers, data=book) as response:
        print(f"Response_code = {response.status_code}")
        print(f"Response test = {response.text}")
        print(f"Response time = {response.elapsed.total_seconds()}")

def get_verlag_test():
    session = get_session()
    headers = {'Content-Type': 'application/json'}
    isbn = {'isbn': '978-3-16-148410-0'}
    with session.get("http://localhost:8080/findBook", params=isbn) as response:
        print(f"Response_code = {response.status_code}")
        print(f"Response test = {response.text}")
        print(f"Response time = {response.elapsed.total_seconds()}s")

####hier könnt ihr die worker anpassen. mehr worker = mehr threads = mehr speed? ist aber python
with concurrent.futures.ThreadPoolExecutor(max_workers=12) as executor:
    print("i am here")

executor.map(post_verlag_test())
####hier muss noch eine for schleife hin(mehr ausführungen der for schleife = mehr worker?)
for x in range(200):
    executor.map(get_verlag_test())