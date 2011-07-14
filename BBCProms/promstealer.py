#!/usr/bin/env python
# -*- coding: UTF-8 -*-
from BeautifulSoup import BeautifulSoup as BS
import urllib2
import pprint
import simplejson
import datetime
import codecs

pp = pprint.PrettyPrinter(indent=4)

prom_dates={'july':[15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31],'august':[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31],'september':[1,2,3,4,5,6,7,8,9,10]}
#prom_dates={'july':[15,16,17]}

#x = urllib2.urlopen("http://www.bbc.co.uk/proms/whats-on/2011/july-15")
#soup = BS(x)
#proms = soup.findAll(name="li", attrs={"class":"prom"})

class Prom(object):
	def cleanString(self,s):
		return s.replace("\u2013",'-')
 
	def __init__(self,name,month,day,url,img):
		self.name = self.cleanString(name)
		self.month = month
		self.day = day
		self.url = url
		self.img = img
		self.year = datetime.datetime.now().year
		self.description = ""
	def setDescription(self,desc):
		self.description = desc;

	def __repr__(self):
		return "%s: %s-%s\t[%s\t%s]" % (self.name, self.month,self.day,self.url,self.img)
	def json(self):
		return """{"name":"%s","year":"%s","month":"%s", "day":"%s", "url":"%s","img":"%s","description":"%s"}""" % (self.name, self.year, self.month,self.day,self.url,self.img,self.description)


prom_by_month = {}
for month in prom_dates.keys():
	prom_by_month[month] = []
	for day in prom_dates[month]:
		x = urllib2.urlopen("http://www.bbc.co.uk/proms/whats-on/2011/%s-%02d" % (month,day))
		#print day,month ###
		soup = BS(x)
		proms = soup.findAll(name="li", attrs={"class":"prom"})
		for p in proms:
			purl = p.find('a',{'class':'promimg'})['href'].split('/')
			puri = "http://www.bbc.co.uk" + p.find('a',{'class':'promimg'})['href']
			pimg = p.find('a',{'class':'promimg'}).contents[1]['src']

			pname = p.find('a',{'class':'promimg'}).contents[1]['alt']
			pdesc = p.find('p',{'class':'desc'}).contents[0]

			pmonth,pday = purl[-2].split('-')
			pid = purl[-1]
			
			#print pmonth, month
			#print pday, day
			if pmonth == month:
				if int(day) == int(pday):
					tmprom = Prom(pname,pmonth,pday,puri,pimg)
					tmprom.setDescription(pdesc)
					prom_by_month[month].append(tmprom)

outfile = codecs.open("proms.json","w","utf-8")

outfile.write( """ { 
		"title": "BBC Proms 2011, json format",
""")
sequence = []
for m in prom_by_month.keys():
	month_json = []
	for _id in range(len(prom_by_month[m])):
		month_json.append(prom_by_month[m][_id].json())
	
	sequence.append(""" "%s":[%s] """ % (m,",".join(month_json)))
		
outfile.write( ",".join(sequence))
outfile.write( """ } """)
