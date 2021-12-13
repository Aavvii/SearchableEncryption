# https://asecuritysite.com/encryption/salsa20

from Crypto.Cipher import AES
from Crypto.Util.Padding import pad, unpad
import random

key = b"1234567812345678"
iv = b"1111111111111111"
n = 16
m = n // 2
ENCODE_TYPE = 'utf-8'

kp = b"5555555555555555"

UNIQUE_SENTENCE = ''


def encrypt_block(block, seed):
    print("Word: ", unpad(block, AES.block_size).decode(encoding='utf-8'))

    E = AES.new(key=key, mode=AES.MODE_CBC, iv=iv)
    ewi = E.encrypt(block)
    print("Encrypted: ", ewi)
    print(len(ewi))
    li = ewi[:n - m]
    random.seed(a=seed)
    si = random.randbytes(n - m)

    f = AES.new(key=kp, mode=AES.MODE_ECB)

    ki = f.encrypt(pad(li, AES.block_size))

    print("Ki: ", ki)

    F = AES.new(key=ki, mode=AES.MODE_ECB)

    Fsi = F.encrypt(pad(si, AES.block_size))[n - m:]

    SC = si + Fsi

    return byte_xor(ewi, SC)


def decrypt_block(cryptotext, seed):
    aesDec = AES.new(key=key, mode=AES.MODE_CBC, iv=iv)

    random.seed(a=seed)
    si = random.randbytes(n - m)

    li = byte_xor(si, cryptotext[:n - m])

    f = AES.new(key=kp, mode=AES.MODE_ECB)

    ki = f.encrypt(pad(li, AES.block_size))

    F = AES.new(key=ki, mode=AES.MODE_ECB)

    Fsi = F.encrypt(pad(si, AES.block_size))[n - m:]

    SC = si + Fsi

    ewi = byte_xor(cryptotext, SC)

    E = AES.new(key=key, mode=AES.MODE_CBC, iv=iv)

    Word = E.decrypt(ewi)

    print(unpad(Word, AES.block_size).decode(encoding='utf-8'))
    return unpad(Word, AES.block_size)


def encrypt_sentence(sentence="", seed=2):
    cryptoText = b''
    for word in sentence.split(' '):
        cryptoText += encrypt_block(
            pad(word.encode(encoding=ENCODE_TYPE), AES.block_size)[:16],
            seed
        )
    global UNIQUE_SENTENCE
    UNIQUE_SENTENCE = cryptoText
    return cryptoText


def decrypt_sentence(cryptotext, seed):
    sentence = b''
    for index in range(0, len(cryptotext), AES.block_size):
        block = cryptotext[index:index + AES.block_size]
        sentence += decrypt_block(block, seed)
        sentence += b' '

    print (sentence.decode(encoding='utf-8'))
    return sentence[:-1]

def search_in_crypto(cryptedWord, ki, cryptotext):

    for index in range(0, len(cryptotext), AES.block_size):
        subword = cryptotext[index:index + AES.block_size]
        xored = byte_xor(subword, cryptedWord)

        unpadSi = xored[0: n - m]
        si = pad(unpadSi, AES.block_size)

        F = AES.new(key=ki, mode=AES.MODE_ECB)

        Fsi = F.encrypt(si)[n - m:]

        print("Comparing: ", xored[n-m:])
        print("To:        ", Fsi)
        if (xored[n-m:] == Fsi):
            return True
    return False



def byte_xor(ba1, ba2):
    return bytes([_a ^ _b for _a, _b in zip(ba1, ba2)])


str = "sad"
print(str * 3)
print(type(str.encode(encoding='utf-8')))
print(len(str))
print(len(str.encode(encoding='utf-8')))

word = b"attack"
decrypt_block(encrypt_block(pad(word, AES.block_size), 100), 100)

sentence = "Ii voi ierta pe acesti indivizi because i am very kind"
print('This will be the encryption: ')
print(decrypt_sentence(encrypt_sentence(sentence, 100), 100))

wordToSearch = b'\xc9\x88t@dA\x8d\x0br\xbb\xcbe\x05\xf1\xab7'
wordToSearchKi = b'\xcc\xf2$kKp\x1b\x9c\x8ez\x1e"\x9f\xdfX\xd2'

print(search_in_crypto(wordToSearch, wordToSearchKi, UNIQUE_SENTENCE))

# TODO: Test more the search