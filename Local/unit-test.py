import unittest
import cryptool
from Crypto.Cipher import AES
from Crypto.Util.Padding import pad, unpad
import numpy as np


class TestCryptool(unittest.TestCase):
    sentence = "Teo e cel mai tare!"
    seed = 96
    word = b"attack"
    new_word = "tare"
    other_word = "tare!"

    def test_encrypt_sentence_decrypt_sentence(self):
        self.assertEqual(cryptool.decrypt_sentence(cryptool.encrypt_sentence(self.sentence, self.seed), self.seed).decode(encoding='utf-8'),
                         self.sentence)

    def test_encrypt_block_decrypt_block(self):
        self.assertEqual(cryptool.decrypt_block(cryptool.encrypt_block(pad(self.word, AES.block_size), 100), 100), self.word)

    def test_search_in_crypto_success(self):
        sentence = "Ii voi ierta pe acesti indivizi because i am very kind"
        print('This will be the encryption: ')
        print(cryptool.decrypt_sentence(cryptool.encrypt_sentence(sentence, 100), 100))

        wordToSearch = b'\xc9\x88t@dA\x8d\x0br\xbb\xcbe\x05\xf1\xab9'
        wordToSearchKi = b'\xcc\xf2$kKp\x1b\x9c\x8ez\x1e"\x9f\xdfX\xd2'

        UNIQUE_SENTENCE = b"\xb8\xf7#\xce \x1b\xbe\x82?\xb8\xde&\x1a[\x137HB\x17O\xb4e\x92\x07\xa4\xff\xd3J\xfe\x1b/\xa7\x8b\x9c4\x9b\n\x1a)\xee\xf2\xed\t'\xd8c?\xcfw\xfc\xb2\xdb\xea\xe5\xa5\xcd\x16\x03\xe6\xf0\xe3\xd0\x0fG\xc1\x1eh\xcb|\x9a]\xa0\x84\xbe\r-<c\x85\x18\xcevky\x92\x90\x91\xbe\x16J\xe3e\x15\xc2h\xa29\xd3&=\xc3\xbe\x91\xf3\xcd\xac>\x833Fs\x1a\x81\x96\xa7\xad\xae80\xea\x84\xba\xa1h\xdbE,\x1a.`\x92.\xfeO\xb0T\xec\xfb\xb1\xea\xf3F\xec\x0e\x04\x18m\xff\x1dz\x00y\xdf\x87H\xab\xc3\x80\xab\xfd_l\xd3\x08\xe1\x9b\x8bKF\x08.\x04T\xdc\xf0\x0e"

        print(cryptool.search_in_crypto(wordToSearch, wordToSearchKi, UNIQUE_SENTENCE))
        self.assertEqual((cryptool.search_in_crypto(wordToSearch, wordToSearchKi, UNIQUE_SENTENCE)), False)

    def test_search_in_crypto_fail(self):
        sentence = "Ii voi ierta pe acesti indivizi because i am very kind"
        print('This will be the encryption: ')
        print(cryptool.decrypt_sentence(cryptool.encrypt_sentence(sentence, 100), 100))

        wordToSearch = b'\xc9\x88t@dA\x8d\x0br\xbb\xcbe\x05\xf1\xab6'
        wordToSearchKi = b'\xcc\xf2$kKp\x1b\x9c\x8ez\x1e"\x9f\xdfX\xd2'
        UNIQUE_SENTENCE = b"\xb8\xf7#\xce \x1b\xbe\x82?\xb8\xde&\x1a[\x137HB\x17O\xb4e\x92\x07\xa4\xff\xd3J\xfe\x1b/\xa7\x8b\x9c4\x9b\n\x1a)\xee\xf2\xed\t'\xd8c?\xcfw\xfc\xb2\xdb\xea\xe5\xa5\xcd\x16\x03\xe6\xf0\xe3\xd0\x0fG\xc1\x1eh\xcb|\x9a]\xa0\x84\xbe\r-<c\x85\x18\xcevky\x92\x90\x91\xbe\x16J\xe3e\x15\xc2h\xa29\xd3&=\xc3\xbe\x91\xf3\xcd\xac>\x833Fs\x1a\x81\x96\xa7\xad\xae80\xea\x84\xba\xa1h\xdbE,\x1a.`\x92.\xfeO\xb0T\xec\xfb\xb1\xea\xf3F\xec\x0e\x04\x18m\xff\x1dz\x00y\xdf\x87H\xab\xc3\x80\xab\xfd_l\xd3\x08\xe1\x9b\x8bKF\x08.\x04T\xdc\xf0\x0e"

        print(cryptool.search_in_crypto(wordToSearch, wordToSearchKi, UNIQUE_SENTENCE))
        self.assertEqual((cryptool.search_in_crypto(wordToSearch, wordToSearchKi, UNIQUE_SENTENCE)), True)


if __name__ == '__main__':
    unittest.main()
