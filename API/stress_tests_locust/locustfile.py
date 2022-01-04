from locust import HttpUser, task


class HelloWorldUser(HttpUser):
    api_url = "http://localhost:8080/searchable-encryption"

    @task
    def hello_world(self):
        self.client.get("/accounts")
        self.client.get("/accounts/em/eli")
        self.client.get("/accounts/1")
        self.client.get("/messages")
        self.client.get("/messages/6")
        self.client.get("/messages/body/6")
        self.client.get("/messages/category/6")
        self.client.get("/messages/sentdate/6")


# Payara Micro URLs:
# http://localhost:8080/
#
# 'ROOT' REST Endpoints:
# GET     /searchable-encryption/accounts
# POST    /searchable-encryption/accounts
# PUT     /searchable-encryption/accounts
# GET     /searchable-encryption/accounts/auth
# GET     /searchable-encryption/accounts/em/{email}
# DELETE  /searchable-encryption/accounts/{accountId}
# GET     /searchable-encryption/accounts/{id}
# GET     /searchable-encryption/messages
# POST    /searchable-encryption/messages
# GET     /searchable-encryption/messages/body/{messageId}
# GET     /searchable-encryption/messages/category/{messageId}
# GET     /searchable-encryption/messages/sentdate/{messageId}
# DELETE  /searchable-encryption/messages/{messageId}
# GET     /searchable-encryption/messages/{messageId}

