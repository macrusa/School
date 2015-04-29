'''
Created on Jan 12, 2015
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
app = Frame(root)
app.grid()

def doCommand():
    print("Do Command")

#label = Label(app, text = "Apollo Windows")
#label.grid()

#Menu on top and submenu is inside Menu
menu = Menu(root)
root.config(menu = menu)

operMenu = Menu(menu, tearoff=0)
menu.add_cascade(label="Operations", menu = operMenu)
operMenu.add_command(label="Invoice", command = doCommand)
operMenu.add_command(label="Quotations", command = doCommand)
operMenu.add_command(label="Bills", command = doCommand)
operMenu.add_command(label="Inventory", command = doCommand)

dataMenu = Menu(menu, tearoff=0)
menu.add_cascade(label="Database", menu = dataMenu)
dataMenu.add_command(label="Vendor", command = doCommand)
dataMenu.add_command(label="Customer", command = doCommand)
dataMenu.add_command(label="Item", command = doCommand)
dataMenu.add_command(label="Item Group", command = doCommand)
dataMenu.add_command(label="Products", command = doCommand)
dataMenu.add_command(label="Miscellaneous", command = doCommand)
dataMenu.add_command(label="Unit", command = doCommand)
dataMenu.add_command(label="History", command = doCommand)

reportMenu = Menu(menu, tearoff=0)
salesMenu = Menu(menu, tearoff= 0)
menu.add_cascade(label="Report", menu = reportMenu)
reportMenu.add_cascade(label="Sales", menu = salesMenu)
    #Submenu of Sales
salesMenu.add_command(label="Daily", command = doCommand)
salesMenu.add_command(label="Other...", command = doCommand)

reportMenu.add_command(label="Customer Statements", command = doCommand)

manageMenu = Menu(menu, tearoff=0)
menu.add_cascade(label="Management", menu = manageMenu)
manageMenu.add_command(label="Accounts", command = doCommand)
    
#Kick off event loop
root.mainloop()

