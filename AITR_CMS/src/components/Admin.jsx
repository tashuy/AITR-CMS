import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { InputElement } from "./ui/InputElement";
import { LoginElement } from "./ui/LoginElement";

const Admin = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const navigate = useNavigate();

  const handleLogin = async (event) => {
    event.preventDefault();
    setError(""); // Clear previous error

    // Check if fields are empty
    if (username.trim() === "" || password.trim() === "") {
      setError("Username and Password are required.");
      return;
    }

    // requests 
    try {

      const adminUrl = "http://localhost:8080/admin";

      const response = await fetch(adminUrl , {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
        
      });
      if (response.ok) {
        const loggedInUsername = username;
        localStorage.setItem("adminUsername", loggedInUsername); // Save username
        // localStorage.setItem("isAdmin", "true"); // optional if you want to check later
      
        // redirect to admin dashboard
        navigate("/admindashboard");
      
      
      } else {
        setError("Invalid username or password.");
        console.log("Login Response:", response);

      }
    } catch (error) {
      console.error("Error during login:", error);
      setError("Something went wrong. Please try again later.");
    }
  };

  return (
    <div className="h-[80vh] flex justify-center items-start bg-gray-300 px-4">
      <div className="w-full max-w-md bg-white shadow-lg rounded-lg p-6 mt-[10vh]">
        <form
          onSubmit={handleLogin}
          id="login-form"
          className="space-y-6 text-black"
        >
          <div className="text-center flex justify-center">
            <img
              className="h-[20vh]"
              src="https://static.vecteezy.com/system/resources/previews/012/210/707/large_2x/worker-employee-businessman-avatar-profile-icon-vector.jpg"
              alt="Admin Avatar"
            />
          </div>
          {/* Admin login page */}
          <div>
          <InputElement 
            placeholder={"username"}
            type={"text"}
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required={true}
          />
          </div>
          <div>
            <input
              className="w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:outline-none"
              placeholder="Password*"
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <LoginElement />

          {error && (
            <p className="text-red-500 text-sm text-center">{error}</p>
          )}

          <div>
            <button
              type="submit"
              className="w-full bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 rounded-md transition duration-300"
            >
              Login
            </button>
          </div>

          <p className="text-center text-gray-600 text-sm">
            Not a member yet?{" "}
            <a href="#" className="text-blue-500 hover:underline">
              Register now
            </a>
          </p>
        </form>
      </div>
    </div>
  );
};

export default Admin;
