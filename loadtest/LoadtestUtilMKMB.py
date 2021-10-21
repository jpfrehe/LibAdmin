import requests
import concurrent.futures
import threading
from random import randrange

thread_local = threading.local()

def get_session():
    if not hasattr(thread_local, "session"):
        thread_local.session = requests.Session()
    return thread_local.session

def post_verlag_test():
    session = get_session()
    headers = {'Content-Type': 'application/json'}
    with session.post("http://localhost:80/addBook", headers=headers, data="{\"title\":\"Die Bibel\",\"author\":\"Deine Mudda\",\"publisher\":\"Christlicher Staat deutscher Nation\",\"isbn\":\"978-3-16-148410-0\"}") as response:
        print(f"Response_code = {response.status_code}")
        print(f"Response test = {response.text}")

####hier könnt ihr die worker anpassen. mehr worker = mehr threads = mehr speed? ist aber python
with concurrent.futures.ThreadPoolExecutor(max_workers=8) as executor:
    print("i am here")

####hier muss noch eine for schleife hin(mehr ausführungen der for schleife = mehr worker?)
for x in range(100):
    executor.map(post_verlag_test())