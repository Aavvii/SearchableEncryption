import sqlite3
import sys

import requests
from PyQt5 import QtWidgets, uic
from PyQt5.QtWidgets import QDialog, QApplication, QWidget
from PyQt5.QtGui import QPixmap


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
        print("aaaa")
        # self.login.clicked.connect(self.gotosendMess)
        print("aaaa--")

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
        else:
            self.gotosendMess()
        # r = requests.get(f'http://127.0.0.1:8082/api/v1/account/username={user}/password={password}/')
        # if r.text is "":
        #     print("No account")
        #     self.error.setText("Invalid username or password")
        # else:
        #     print(f"{r.text}")
        #     self.error.setText("Account found")

        # print("Ceva sigur s-intamplat 000")
        # # sendMessage = SendMessage(user)
        # print("Ceva sigur s-intamplat")
        # self.gotosendMess()


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

    def fillprofileScreenfunction(self):
        print("here")
        email = self.email.text()
        firstname = self.firstname.text()
        lastname = self.lastname.text()
        id = self.id + 1
        self.id = self.id + 1

        myobj = {'id': id, 'firstname': firstname, 'lastname': lastname, 'email': email, 'username': self.user,
                 'password': self.password}

        print(myobj)

        headers = {
            'Content-type': 'application/json',
            'Accept': 'application/json'
        }

        x = requests.post('http://127.0.0.1:8082/api/v1/account', json=myobj, headers=headers)
        print(x.text)


class SendMessage(QDialog):
    def __init__(self, user):
        self.user = user
        super(SendMessage, self).__init__()
        uic.loadUi("WORK2.ui", self)
        self.send.clicked.connect(self.sendToServer)

    def sendToServer(self):
        message = self.email.text()
        print(message)


        myobj = {'message': message, 'user': self.user}

        print(myobj)

        headers = {
            'Content-type': 'application/json',
            'Accept': 'application/json'
        }

        try:
            x = requests.post('http://127.0.0.1:8082/api/v1/message', json=myobj, headers=headers)
            print(x.text)
        except:
            print("Could not send message")



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
