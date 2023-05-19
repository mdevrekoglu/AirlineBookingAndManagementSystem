import random

# List of possible destinations
destinations = ["Tokyo", "Delhi", "Shanghai", "Sao Paulo", "Mumbai", "Mexico City", "Beijing", "Osaka", "Cairo", "New York City", 
"Moscow", "Bangkok", "Dhaka", "Karachi", "Buenos Aires", "Istanbul", "Kolkata", "Manila", "Lagos", "Rio de Janeiro", 
"Tianjin", "Kinshasa", "Guangzhou", "Los Angeles", "Shenzhen", "Lahore", "Paris", "Bogota", "Jakarta", "Chennai", 
"Lima", "Bangalore", "London", "Ho Chi Minh City", "Nagoya", "Chicago", "Hyderabad", "Chongqing", "Tehran", "Wuhan", 
"Chengdu", "Hong Kong", "Foshan", "Essen", "Hangzhou", "Madrid", "Pune", "Houston", "Dallas", "Toronto", "Dar es Salaam", 
"Singapore", "Philadelphia", "Atlanta", "Khartoum", "Barcelona", "Johannesburg", "Saint Petersburg", "Qingdao", "Dalian", 
"Washington, D.C.", "Yangon", "Alexandria", "Guadalajara", "Houston The WoodlandsSugar Land", "Kuala Lumpur", "Miami", 
"Belgrade", "Sana'a", "Shenyang", "Casablanca", "Nanjing", "Kabul", "Hanoi", "Xiamen", "Xi'an", "Montreal", "Riyadh", 
"Tangshan", "Khartoum North", "Baghdad", "Ankara", "Sydney", "Addis Ababa", "Surat", "Santiago", "Nairobi", "Melbourne", 
"Jeddah", "Monterrey", "Phoenix", "Brasilia", "Durban", "Lucknow", "San Francisco", "Izmir", "Mashhad", "Kanpur", 
"Ludhiana", "Zibo", "Kampala", "Surabaya", "Milan", "Kananga", "Caracas", "Urumqi", "Giza", "Mandalay", "Accra", 
"Izmit", "Manhattan", "Kobe", "Lanzhou", "La Paz", "Patna", "Baku", "Kumasi", "Mbuji-Mayi", "Kaohsiung", "Varanasi", 
"Kaduna", "Santo Domingo", "Valencia", "Taichung", "Brisbane", "Vancouver", "Yerevan", "Tunis", "Asuncion", "Tabriz", 
"Bishkek", "Kyoto", "Cape Town", "Port Harcourt", "Faisalabad", "Haifa", 
"Jerusalem", "Almaty", "San Diego", "Jinan", "Kochi", "Hefei", "Taizhou, Zhejiang", "Tijuana", "Vadodara", "Bandung", 
"Bursa", "Makassar", "Saidu Sharif", "Rawalpindi", "Minsk", "Karaj", "Antananarivo", "Semarang", "L", "Balikesir", "Hakkari", "Hatay",
"Kilis", "Mardin", "Nevsehir", "Sirnak", "Tunceli", "Yalova", "Bartin", "Bayburt", "Karabuk", "Ardahan", "Igdir", "Yozgat", "Aksaray",
"Kirikkale", "Nigde", "Bilecik", "Bolu", "Duzce", "Kastamonu", "Sinop", "Tokat", "Zonguldak", "Bingol", "Bitlis", "Elazig", "Erzincan",
"Erzurum", "Kars", "Malatya", "Mus", "Van", "Agri", "Batman", "Diyarbakir", "Siirt", "Sanliurfa", "Adiyaman", "Gaziantep", "Kahramanmaras",
"Sinop", "Samsun", "Ordu", "Giresun", "Trabzon", "Rize", "Artvin", "Gumushane"]

# Generate 10,000 flights
flights = []
for i in range(10000):
    # Generate a random flight number with 5 digits
    flightNo = i
    
    # Choose a random destination from the list
    # Destination should be in this format: Istanbul-Paris
    # Start point and end point should be different
    start = random.choice(destinations)
    end = random.choice(destinations)
    while start == end:
        end = random.choice(destinations)
    destination = f"{start}-{end}"
    
    
    # Generate a random date between Jan 1, 2023 and Dec 31, 2023 format should be: 01.01.2023
    date = f"2023-{random.randint(1, 12):02d}-{random.randint(1, 28):02d}"
    
    # Generate a random flying time between 1 and 12 hours
    flying_hours = random.randint(1, 23)
    flying_minutes = random.randint(0, 59)
    flying_time = f"{flying_hours:02d}:{flying_minutes:02d}"
    
    # Generate a random price between 50 and 500
    price = random.randint(50, 500)
    
    # Generate a random number of available seats between 50 and 200
    available_seats = 120
    
    # Generate a random list of -1's and 1's for the seat availability in the next 100 minutes
    seat_availability = ';'.join(["-1" for i in range(100)])
    
    # Combine all the data into a string in the specified format
    flight_data = f"{flightNo}/{destination}/{date}T{flying_time}/{price}/{available_seats}/{seat_availability}"
    
    # Append the flight data to the list of flights
    flights.append(flight_data)

# Open a file for writing
with open('flights.txt', 'w') as file:
    # Write each string on a new line
    for flight in flights:
        file.write(flight + '\n')
    # Add more strings as needed