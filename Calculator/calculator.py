
def add(x, y):
    return x + y

def subtract(x, y):
    return x - y

def multiply(x, y):
    return x * y

def divide(x, y):
    return x / y

print("Select an operation: -\n \
    1. Add \n \
    2. Subtract \n \
    3. Multiply \n \
    4. Divide\n")


choice = int(input("Please select from 1, 2, 3, or 4: "))
num1 = int(input("What is the first number you'd like to use?: "))
num2 = int(input("What is the second number you'd like to use?: "))

if choice == 1:
    print(num1, "+",  num2,  "=",  add(num1, num2))

elif choice == 2:
    print(num1, "-", num2, "=", subtract(num1, num2))

elif choice == 3:
    print(num1, "*", num2, "=", multiply(num1, num2))

elif choice == 4:
    print(num1, "/", num2, "=", divide(num1, num2))

