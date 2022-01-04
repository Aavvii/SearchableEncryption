import requests

# ~~~TEST LOGIN~~~

# r = requests.post(
#     url=f'http://127.0.0.1:8080/searchable-encryption/accounts/auth',
#     json={
#     'username': 'eli',
#     'password': 'eli'
# },
#     headers={
#         'Content-type': 'application/json',
#         'Accept': 'application/json'
#     }
# )
#
# print(r.text)
# print(r.status_code)

# ~~~ TEST CREATE ACCOUNT ~~~
myobj = {'email': 'email@email.email', 'username': 'Shanti2000', 'password': 'Shanti2000'}

print(myobj)

headers = {
    'Content-type': 'application/json',
    'Accept': 'application/json'
}

x = requests.post('http://localhost:8080/searchable-encryption/accounts', json=myobj, headers=headers)
print(x.text)
