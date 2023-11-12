import os,ssl
from email.message import EmailMessage
import smtplib

email_sender = "suhasmkumar66@gmail.com"
email_password = 'mzal hfmd cvle vbft'
email_receiver = 'suhasmkumar69@gmail.com'

subject = 'Your Order has been placed'
body = """
Dear Customer,

Your order has been placed and your order confirmation Number is ABC123JHAS

Regards,
Pharm-e-cart
"""
em = EmailMessage()
em['From'] = email_sender
em['To'] = email_receiver
em['Subject'] = subject
em.set_content(body)

context = ssl.create_default_context()

with smtplib.SMTP_SSL('smtp.gmail.com',465,context=context) as smtp:
    smtp.login(email_sender,email_password)
    smtp.sendmail(email_sender,email_receiver,em.as_string())