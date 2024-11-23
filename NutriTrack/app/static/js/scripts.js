document
  .getElementById("add-meal-form")
  .addEventListener("submit", async (event) => {
    event.preventDefault();

    const mealName = document.getElementById("meal_name").value;
    const calories = document.getElementById("calories").value;
    const username = window.location.search.split("=")[1]; // Extract username from URL

    const response = await fetch("/add_meal", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, meal_name: mealName, calories }),
    });

    const data = await response.json();

    const mealsList = document.getElementById("meals-list");
    mealsList.innerHTML = "";
    data.meals.forEach((meal) => {
      const li = document.createElement("li");
      li.textContent = `${meal.name}: ${meal.calories} calories`;
      mealsList.appendChild(li);
    });

    document.getElementById("meal_name").value = "";
    document.getElementById("calories").value = "";
  });
