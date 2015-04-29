'''
Created on Jan 13, 2015

@author: Nancy
'''
#Import GUI
from tkinter import*

#Window
root = Tk()

#Modify window
root.title("Apollo Windows")
root.geometry("1000x500")

#Apps
def doCommand():
    print("Do Command") 
    
def userName(event):
    print("username")
 
var = StringVar()
#THIS HERE IS A LIST
optionList = ("Username1", "Username2", "Username3")
var.set("Usernames")
OptionMenu(root, var, optionList[0], command=userName).pack()

password = Label(root, text="Password")
password.pack(side=LEFT, fill=X)


input = Entry(root, bd=5)
input.pack(side=LEFT, fill=X)

#Stores inputted password into inputPassword
inputPassword = input.get()

#Kick off event loop
root.mainloop()