import sqlite3
import sys

import requests
from PyQt5 import QtWidgets, uic
from PyQt5.QtWidgets import QDialog, QApplication, QWidget
from PyQt5.QtGui import QPixmap
import cryptool
from datetime import datetime

user_logged_in = ''

class WelcomeScreen(QDialog):
    def __init__(self):
        super(WelcomeScreen, self).__init__()
        uic.loadUi("welcomescreen.ui", self)
        self.login.clicked.connect(self.gotologin)
        self.create.clicked.connect(self.gotocreate)

    def gotologin(self):
        login = LoginScreen()
        widget.addWidget(login)
        widget.setCurrentIndex(widget.currentIndex() + 1)

    def gotocreate(self):
        create = CreateAccScreen()
        widget.addWidget(create)
        widget.setCurrentIndex(widget.currentIndex() + 1)


class LoginScreen(QDialog):
    def __init__(self):
        super(LoginScreen, self).__init__()
        uic.loadUi("login.ui", self)
        self.passwordfield.setEchoMode(QtWidgets.QLineEdit.Password)
        self.login.clicked.connect(self.loginfunction)

    def gotosendMess(self):
        send = SendMessage(self.user)
        widget.addWidget(send)
        widget.setCurrentIndex(widget.currentIndex() + 1)

    def loginfunction(self):
        self.user = self.emailfield.text()
        password = self.passwordfield.text()
        #
        if len(self.user) == 0 or len(password) == 0:
            self.error.setText("Please input all fields.")
        # else:
        #     print(self.user)
        #     self.gotosendMess()

        else:
            r = requests.post(
                url=f'http://127.0.0.1:8080/searchable-encryption/accounts/auth',
                json={
                    "username": self.user,
                    "password": password
                },
                headers={
                    'Content-type': 'application/json',
                    'Accept': 'application/json'
                }
            )
            if r.status_code != 200:
                print("No account")
                self.error.setText("Invalid username or password")
            else:
                print(f"{r.text}")
                self.error.setText("Account found")
                global user_logged_in
                user_logged_in = r.json()
                self.gotosendMess()


class CreateAccScreen(QDialog):
    def __init__(self):
        super(CreateAccScreen, self).__init__()
        uic.loadUi("createacc.ui", self)
        self.passwordfield.setEchoMode(QtWidgets.QLineEdit.Password)
        self.confirmpasswordfield.setEchoMode(QtWidgets.QLineEdit.Password)
        self.signup.clicked.connect(self.signupfunction)

    def signupfunction(self):
        user = self.emailfield.text()
        password = self.passwordfield.text()
        confirmpassword = self.confirmpasswordfield.text()

        if len(user) == 0 or len(password) == 0 or len(confirmpassword) == 0:
            self.error.setText("Please fill in all inputs.")

        elif password != confirmpassword:
            self.error.setText("Passwords do not match.")
        else:
            # conn = sqlite3.connect("shop_data.db")
            # cur = conn.cursor()
            #
            # user_info = [user, password]
            # cur.execute('INSERT INTO login_info (username, password) VALUES (?,?)', user_info)
            #
            # conn.commit()
            # conn.close()
            #
            fillprofile = FillProfileScreen(user, password)
            widget.addWidget(fillprofile)
            widget.setCurrentIndex(widget.currentIndex() + 1)


class FillProfileScreen(QDialog):
    def __init__(self, user, password):
        super(FillProfileScreen, self).__init__()
        self.id = 0
        uic.loadUi("fillprofile.ui", self)
        self.image.setPixmap(QPixmap('placeholder.png'))
        self.signup.clicked.connect(self.fillprofileScreenfunction)
        self.user = user
        self.password = password

    def gotologin(self):
        login = LoginScreen()
        widget.addWidget(login)
        widget.setCurrentIndex(widget.currentIndex() + 1)

    def fillprofileScreenfunction(self):
        print("here")
        email = self.email.text()
        # firstname = self.firstname.text()
        # lastname = self.lastname.text()
        # id = self.id + 1
        self.id = self.id + 1

        myobj = {'email': email, 'username': self.user, 'password': self.password}

        print(myobj)

        headers = {
            'Content-type': 'application/json',
            'Accept': 'application/json'
        }

        x = requests.post('http://localhost:8080/searchable-encryption/accounts', json=myobj, headers=headers)
        print(x.text)
        self.gotologin()


class SendMessage(QDialog):
    def __init__(self, user):
        self.user = user
        self.receiver_id = False
        super(SendMessage, self).__init__()
        uic.loadUi("WORK2.ui", self)
        self.send.clicked.connect(self.sendToServer)
        self.createaccbutton.clicked.connect(self.search_user)

    def search_user(self):
        to_user = self.password.text()
        r = requests.get(
            url=f'http://127.0.0.1:8080/searchable-encryption/accounts/id/{to_user}'
        )
        if r.status_code != 200:
            print("No account")
            self.searchLable.setText("The user doesn't exist!!!")
        else:
            print(f"{r.text}")
            self.receiver_id = int(r.text)
            self.searchLable.setText("The user exists. U can send message to him <3")

    def sendToServer(self):
        message = self.email.text()
        print(message)
        encrypted_message = cryptool.encrypt_sentence(message, 69)
        print(encrypted_message)

        print(cryptool.decrypt_sentence(encrypted_message, 69))

        if self.receiver_id and message:
            # global user_logged_in
            myobj = {'text': str(encrypted_message), 'sender_id': user_logged_in['id'], 'receiver_id': self.receiver_id, "sentDate": str(datetime.now()), "category": "spam"}

            print(myobj)

            headers = {
                'Content-type': 'application/json'
            }

            try:
                x = requests.post('http://127.0.0.1:8080/searchable-encryption/messages', json=myobj, headers=headers)
                print(x.text)
                print("Message sent succesfully")
                self.ifSendSucc.setText("Message sent succesfully")
            except:
                self.ifSendSucc.setText("Server error")
        else:
            self.ifSendSucc.setText("The message is Empty OR you didn't search for a user >:(")


# main
app = QApplication(sys.argv)
welcome = WelcomeScreen()
widget = QtWidgets.QStackedWidget()
widget.addWidget(welcome)
widget.setFixedHeight(800)
widget.setFixedWidth(1200)
widget.show()
try:
    sys.exit(app.exec_())
except:
    print("Exiting")
