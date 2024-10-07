import random

number = random.randrange(1, 21)

print("A number has been generated from 1-20. Try to guess which number it is!\n")

choice = None

while choice != number:
    choice = int(input("Enter your guess: "))

    if choice < number:
        print("Too low. Try again!")
    elif choice > number:
        print("Too high. Try again!")
    else:
        print("You got it right! The correct number was", number)


