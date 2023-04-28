import random

# List of possible destinations
names = ['Avery', 'Ella', 'Landon', 'Bridget', 'Ryder', 'Aria', 'Milo', 'Camila', 'Max', 'Gianna', 
'Oliver', 'Ruby', 'Levi', 'Nova', 'Asher', 'Hazel', 'Jackson', 'Evelyn', 'Sebastian', 'Ivy', 
'Ethan', 'Aurora', 'Lucas', 'Penelope', 'Lincoln', 'Luna', 'Caleb', 'Emilia', 'Noah', 'Charlotte', 
'Grayson', 'Stella', 'Evelyn', 'Mia', 'Daniel', 'Aaliyah', 'Logan', 'Chloe', 'David', 'Sofia', 
'Aiden', 'Harper', 'Mason', 'Zoe', 'Eli', 'Ellie', 'Hunter', 'Lila', 'Owen', 'Emma', 
'Liam', 'Ava', 'Carter', 'Scarlett', 'James', 'Madison', 'Benjamin', 'Lily', 'Luke', 'Natalie', 
'Samuel', 'Riley', 'Wyatt', 'Aria', 'Joseph', 'Liliana', 'William', 'Audrey', 'Michael', 'Mila', 
'Gabriel', 'Elena', 'Ryan', 'Hannah', 'Nicholas', 'Lucy', 'Christian', 'Julia', 'Nathan', 'Abigail', 
'Isaac', 'Avery', 'Anthony', 'Samantha', 'Joshua', 'Elizabeth', 'Elijah', 'Eleanor', 'Isabelle', 'Victoria', 
'Brayden', 'Aubrey', 'Alexander', 'Grace', 'Connor', 'Addison', 'Savannah', 'Alyssa', 'Zachary', 'Nora', 
'Dylan', 'Aaliyah', 'Maddox', 'Claire', 'Harrison', 'Leah', 'Ezekiel', 'Caroline', 'Grayson', 'Hailey', 
'Stella', 'Alice', 'Jordan', 'Katherine', 'Julian', 'Eva', 'Mia', 'Naomi', 'Jason', 'Lydia', 
'Liliana', 'Isabella', 'Mateo', 'Emily', 'Austin', 'Olivia', 'Mackenzie', 'Brooklyn', 'Kaylee', 'Aurora', 
'Leah', 'Makayla', 'Leonardo', 'Sophie', 'Cameron', 'Isabelle', 'Jaxson', 'Ellie', 'Aria', 'Maya', 
'Penelope', 'Hazel', 'Eleanor', 'Aria', 'Mason', 'Avery', 'Evelyn', 'Amelia', 'Aiden', 'Khloe', 
'Abigail', 'Peyton', 'Alexander', 'Bella', 'Ella', 'Madelyn', 'Anthony', 'Makayla', 'Jasmine', 'Addison', 
'Caroline', 'Hailey', 'Levi', 'Samantha', 'Sofia', 'Grace', 'Landon', 'Lily', 'Elliot', 'Anna', 
'Camila', 'Aaliyah', 'Isabelle', 'Savannah', 'Charlotte', 'Eva', 'Lucas', 'Zoe', 'Julia', 'Kinsley',
'Ahmet', 'Hakki', 'Mehmet', 'Hatice', 'Yusuf', 'Fatma', 'Ali', 'Ayse', 'Mustafa', 'Emine', 'Faruk',
'Zeynep', 'Bilal', 'Merve', 'Selin', 'Suleyman', 'Sultan', 'Sule', 'Sahin', 'Sahika', 'Sahin',
'Nur', 'Nuray', 'Nurten', 'Nurcan', 'Nurullah', 'Nurten', 'Nuray', 'Nurcan', 'Fatma', 'Aysegul']

surnames = ['Yilmaz', 'Kara', 'Demir', 'Ozdemir', 'Can', 'Kaya', 'Sahin', 'Aydin', 'Guler', 'Yildiz', 'Gok', 
'Yigit', 'Sari', 'Ozkan', 'Yalcin', 'Aksoy', 'Erdogan', 'Erdogan', 'Arslan', 'Ay', 'Ozturk', 'Ates', 
'Gul', 'Demirci', 'Kurt', 'Altun', 'Akin', 'Bakir', 'Tekin', 'Cicek', 'Tunc', 'Dogan', 'Ozcelik', 
'Yavuz', 'Caliskan', 'Ozmen', 'Guzel', 'Basar', 'Ozer', 'Kurtul', 'Kilic', 'Koc', 'Sahbaz', 'Savas', 
'Bayram', 'Yuce', 'Kasikci', 'Cengiz', 'Kaynak', 'Uysal', 'Kartal', 'Kizil', 'Cetin', 'Bostanci', 
'Avci', 'Gurbuz', 'Uzun', 'Tas', 'Turan', 'Kiran', 'Eksi', 'Alkan', 'Koca', 'Gundogdu', 'Ibis', 
'Toprak', 'Yigitbas', 'Er', 'Kan', 'Sengul', 'Sarioglu', 'Karadag', 'Ugur', 'Bayrak', 'Eryilmaz', 
'Genc', 'Koyuncu', 'Akkus', 'Aydinoglu', 'Ince', 'Gunduz', 'Yavuzer', 'Unlu', 'Bal', 'Arda', 
'Cavdar', 'Beyaz', 'Ozkanli', 'Yilmazer', 'Coban', 'Uyanik', 'Aras', 'Dag', 'Ersoy', 'Doganay', 
'Kocabas', 'Sahiner', 'Sahingoz', 'Kavak', 'Oruc', 'Aydinli', 'Celik', 'Kocak', 'Cin', 'Atabay', 
'Turanli', 'Sevinc', 'Bulut', 'Seyhan', 'Yasar', 'Sah', 'Sahinoglu', 'Ayvaz', 'Kocaman', 'Pehlivan', 
'Dagdelen', 'Kocaoglu', 'Onal', 'Kocabey', 'Balci', 'Koyuncu', 'Ayvaci', 'Karaman', 'Ozkaya', 'Baser', 
'Goksel', 'Kaygusuz', 'Yazici', 'Altunbas', 'Bekar', 'Kaymaz', 'Gonenc', 'Akpinar', 'Karakus', 'Canbaz', 
'Sen', 'Yenigun', 'Kir', 'Karadag', 'Doganer', 'Yoldas', 'Kilinc', 'Sahiner', 'Tumturk', 'Tunca', 
'Aygun', 'Kosar', 'Kocakaya', 'Ozel', 'Yayla', 'Ozen', 'Karahan', 'Gokce', 'Yavuzcan', 'Kazanci',
'Smith', 'Johnson', 'Williams', 'Jones', 'Brown', 'Davis', 'Miller', 'Wilson', 'Moore', 'Taylor', 
'Anderson', 'Thomas', 'Jackson', 'White', 'Harris', 'Martin', 'Thompson', 'Garcia', 'Martinez', 'Robinson', 
'Clark', 'Rodriguez', 'Lewis', 'Lee', 'Walker', 'Hall', 'Allen', 'Young', 'King', 'Wright', 
'Scott', 'Green', 'Baker', 'Adams', 'Nelson', 'Carter', 'Mitchell', 'Perez', 'Roberts', 'Turner', 
'Phillips', 'Campbell', 'Parker', 'Evans', 'Edwards', 'Collins', 'Stewart', 'Sanchez', 'Morris', 'Rogers', 
'Reed', 'Cook']

# Mehmet/Devrekoglu/mehmetdevrekoglu01@gmail.com/3162/05437280031/0
# Name/Surname/Email/Password/Phone/0
# Generate 1000 customers
customers = []
customers.append("Mehmet/Devrekoglu/mehmetdevrekoglu01@gmail.com/123456789/05437280031/1/;")
customers.append("Fatih/Bahcecioglu/fatih@gmail.com/123456789/05050172082/1/;")
customers.append("Arda/Aslaner/arda@gmail.com/123456789/05050172084/1/;")

for i in range(1000):
    # Generate a random name
    name = random.choice(names)
    
    # Generate a random surname
    surname = random.choice(names)
    
    # Email and phone number should be unique and random
    email = f"{name}{surname}{random.randint(1, 10000)}" + random.choice(["@gmail.com", "@hotmail.com", "@yahoo.com", "@outlook.com"])

    # Generate a random password with at least 8 characters
    size = random.randint(8, 16)
    password = ''.join([random.choice("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789") for i in range(size)])

    # Generate a random phone number with 11 digits 05xxxxxxxxx
    phone = "05" + ''.join([random.choice("0123456789") for i in range(9)])

    # Add the customer to the list
    customers.append(f"{name}/{surname}/{email}/{password}/{phone}/0/;")


# Open a file for writing
with open('customers.txt', 'w') as file:
    # Write each string on a new line
    for customer in customers:
        file.write(customer + '\n')
    # Add more strings as needed