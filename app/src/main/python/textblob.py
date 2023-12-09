import speech_recognition as s

sr=s.Recognizer()
print("Hello Students")
with s.Microphone()as m:
    print("Say Something!")
    sr.adjust_for_ambient_noise(m)
    audio=sr.listen(m,timeout=4)
    '''print(type(sr))'''

try:
    query=sr.recognize_google(audio)
    print("Google Speech Recognition think you said:\n"+query)

except s.UnknownValueError:
    print("Google Speech Recognition could not understand your voice!")

except s.RequestError as e:
    print("Could not request results from Google Speech Recognition Service;{0}".format(e))


from textblob.en import Spelling
from textblob import TextBlob

pathToFile = "C:\\Users\\Public\\train.txt"
spelling = Spelling(path = pathToFile)
text = "We are wabbit gabbit coo cool poon puhlay thills rewember pinano nana wed dreen thwim dreat gat threet dame wadio gup dhpot thcale tate thimple gad wead gan buth thit bananana pinanio thunday thaturday mith thee thaw"

words = query.split()
corrected = " "
for i in words :
    corrected = corrected +" "+ spelling.suggest(i)[0][0] # Spell checking word by word

print(corrected)

# Load the tokenizer
from transformers import BertTokenizer, LineByLineTextDataset

vocab_file_dir = '/content/vocab.txt'

tokenizer = BertTokenizer(vocab_file_dir)
sentence = 'There are many spoons on the table.'

encoded_input = tokenizer.tokenize(sentence)
#print(encoded_input)
# print(encoded_input['input_ids'])




dataset= LineByLineTextDataset(
    tokenizer = tokenizer,
    file_path = '/content/sentences.txt',
    block_size = 128  # maximum sequence length
)

print('No. of lines: ', len(dataset))




test_dataset= LineByLineTextDataset(
    tokenizer = tokenizer,
    file_path = '/content/tes_sentences.txt',
    block_size = 128  # maximum sequence length
)

print('No. of lines: ', len(test_dataset))




from transformers import BertConfig, BertForMaskedLM, DataCollatorForLanguageModeling

config = BertConfig(
    vocab_size=50000,
    hidden_size=768,
    num_hidden_layers=6,
    num_attention_heads=12,
    max_position_embeddings=512
)

model = BertForMaskedLM(config)
print('No of parameters: ', model.num_parameters())


data_collator = DataCollatorForLanguageModeling(
    tokenizer=tokenizer, mlm=True, mlm_probability=0.15
)




from transformers import Trainer, TrainingArguments

training_args = TrainingArguments(
    output_dir='/content/working/',
    #evaluation_strategy = "epoch",
    overwrite_output_dir=True,
    num_train_epochs=3,
    learning_rate=2e-5,
    per_device_train_batch_size=32,
    save_steps=10_000,
    save_total_limit=2,
)




trainer = Trainer(
    model=model,
    args=training_args,
    data_collator=data_collator,
    train_dataset=dataset,
    eval_dataset=test_dataset,
    #prediction_loss_only=True,
)




trainer.train()
trainer.save_model('/content/working/')




#!python --version
import nltk
from nltk.stem import WordNetLemmatizer
from nltk.tokenize import sent_tokenize,word_tokenize
from nltk.corpus import stopwords
from nltk.probability import FreqDist
import matplotlib.pyplot as plt

import torch
#!pip install pytorch-pretrained-bert
# !apt install enchant
# !apt install -qq enchant
#!pip install pyenchant

#from pytorch_pretrained_bert import BertTokenizer, BertForMaskedLM
import re
import nltk
#!pip3 install pyenchant

#from enchant.checker import SpellChecker
#!pip install pyspellchecker
from spellchecker import SpellChecker
from difflib import SequenceMatcher

# text="""I like wed color."""
# query="""I like wed color."""
# text="""I like vabbit."""
# query="""I like vabbit."""
# text="""Ahmad phulay a football with his friends."""
# query="""Ahmad phulay a football with his friends."""
# text="""You live a very thimple life."""
# query="""You live a very thimple life."""
# text="""My friend like to listen the wadio."""
# query="""My friend like to listen the wadio."""
# text="""Ali likes panana."""
# query="""Ali likes panana."""
text="""She has designing thkills."""
query="""She has designing thkills."""
# text="""Ali likes thalad"""
# query="""Ali likes thalad"""
# text="""Ahmad buys different thoaps"""
# query="""Ahmad buys different thoaps"""
# text="""Ali likes thoop"""
# query="""Ali likes thoop"""
# text="""Mostly child eat with thpoon"""
# query="""Mostly child eat with thpoon"""
# text="""She likes to eat in clean gishes"""
# query="""She likes to eat in clean gishes"""
# text="""Ali likes to beat the grum"""
# query="""Ali likes to beat the grum"""

text_original = str(text)
print("Text before Mask")
print (text_original)
print("\n")

# using enchant.checker.SpellChecker, identify incorrect words
d = SpellChecker()
words = text.split()
incorrectwords = [w for w in words if d.unknown([w])]
# using enchant.checker.SpellChecker, get suggested replacements
#suggestedwords = [d.suggest(w) for w in incorrectwords]
# replace incorrect words with [MASK]
for w in incorrectwords:
    text = text.replace(w, '[MASK]')
    text_original = text_original.replace(w, '[MASK]')

print("Text After Mask")
print(text)
from textblob.en import Spelling
from textblob import TextBlob
pathToFile = "/content/vocabb.txt"
spelling = Spelling(path = pathToFile)
#text = "We are wabbit gabbit coo cool poon puhlay thills rewember pinano nana wed dreen thwim dreat gat threet dame wadio gup dhpot thcale tate thimple gad wead gan buth thit bananana pinanio thunday thaturday mith thee thaw"
words = query.split()
corrected = " "
for i in words :
    corrected = corrected +" "+ spelling.suggest(i)[0][0] # Spell checking word by word
print(corrected)
print("\n")
#fill_mask(text)




