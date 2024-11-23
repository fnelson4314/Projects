from flask import Flask, render_template, request, jsonify

app = Flask(__name__)

# Mock database to store user data
users = {}

@app.route('/')
def home():
    return render_template('index.html')

@app.route('/dashboard', methods=['POST', 'GET'])
def dashboard():
    if request.method == 'POST':
        # Collect user data
        username = request.form.get('username')
        height = float(request.form.get('height'))
        age = int(request.form.get('age'))
        activity_level = float(request.form.get('activity_level'))

        # Calculate calorie goal (simplified BMR calculation)
        calorie_goal = 10 * height + 6.25 * age + 5 * activity_level - 5

        # Store user data in a mock database
        users[username] = {
            "calorie_goal": calorie_goal,
            "calories_consumed": 0,
            "meals": []
        }

        return render_template('dashboard.html', username=username, user=users[username])

    # For GET requests
    username = request.args.get('username')
    return render_template('dashboard.html', username=username, user=users.get(username, {}))

@app.route('/add_meal', methods=['POST'])
def add_meal():
    data = request.json
    username = data['username']
    meal_name = data['meal_name']
    calories = int(data['calories'])

    # Update user data
    users[username]['meals'].append({"name": meal_name, "calories": calories})
    users[username]['calories_consumed'] += calories

    return jsonify(users[username])

if __name__ == '__main__':
    app.run(debug=True)
