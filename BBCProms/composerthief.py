#!/usr/bin/env python
# -*- coding: UTF-8 -*-
from BeautifulSoup import BeautifulSoup as BS
import urllib2
import pprint
import simplejson
import datetime
import codecs

class Composer(object):
	def __init__(self,_url,_name):
		self.url = "http://www.bbc.co.uk" + _url
		self.name = _name
	def __repr__(self):
		return self.name
	def toJSON(self):
		return """{"name":"%s","url":"%s"}""" % (self.name,self.url)


year = datetime.datetime.now().year
composerlist = "http://www.bbc.co.uk/proms/whats-on/%d/composers" % year
x = urllib2.urlopen(composerlist)
soup = BS(x)
atoz = soup.find("div",{"id":"a_to_z"})
l_atoz = atoz.findAll("li")
composers = []

for composer in l_atoz:
	cname = composer.contents[0].contents[0]
	curl = composer.contents[0]['href']
	composers.append(Composer(_url=curl,_name=cname))

composer_json = []
for cc in composers:
	composer_json.append(cc.toJSON())	


outfile = codecs.open("composers.json","w","utf-8")

outfile.write( """ { 
		"title": "BBC Proms - Composers %d, json format",
		"artists": [%s]
		} """ % (year,",".join(composer_json)))
outfile.close()
	
