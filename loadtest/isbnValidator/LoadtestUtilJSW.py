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
    with session.get("https://isbnchecker.azurewebsites.net/api/ValidateISBN?isbn=978-3-16-148410-0", headers=headers) as response:
        print(f"Response_code = {response.status_code}")
        print(f"Response test = {response.text}")

####hier könnt ihr die worker anpassen. mehr worker = mehr threads = mehr speed? ist aber python
with concurrent.futures.ThreadPoolExecutor(max_workers=12) as executor:
    print("i am here")

####hier muss noch eine for schleife hin(mehr ausführungen der for schleife = mehr worker?)
for x in range(200):
    executor.map(post_verlag_test())