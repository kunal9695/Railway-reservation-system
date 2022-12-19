import "bootstrap/dist/css/bootstrap.min.css";
import React, { Component } from "react";
import { Link } from "react-router-dom";
import "./UserRegister.css";
import userregister from '../assets/userregister.png';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useHistory, useLocation } from "react-router-dom";
import _get from "lodash.get";
import history from '../history'
import Header from "./Header";
import Admin from "../assets/Admin.png"


toast.configure()



/**To Show Add Toastify Text */
const notify = () => {
  toast.success('User Added Successfully!!',
    {
      position: "top-center",
      autoClose: 3000
    });

}

const error = () => {
  toast.error("UserName already taken.", {
    position: "top-center",
    autoClose: 2000,
  });
};

const error1 = () => {
  toast.error("Email already taken.", {
    position: "top-center",
    autoClose: 2000,
  });
};


export default class UserRegister extends Component {
  constructor() {
    super();
    this.state = {
      email: "",
      username: "",
      password: "",
    };
    this.register = this.register.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }



  register(e) {
    e.preventDefault();
    fetch("http://localhost:8080/api/auth/signup", {
      method: "POST",
      headers: {
        "content-type": "application/json",
        accept: "application/json",
        "Access-Control-Allow-Origin": "*",
      },
      body: JSON.stringify({
        username: this.state.username,
        email: this.state.email,
        password: this.state.password,
        roles: ["user"]
      }),
    })
      .then((response) => response.json())
      .then((response) => {
        if (response.message === "User registered successfully!") {
          notify();
          history.push("/userlogin");
          window.location.reload()
        }
        else if (response.message === "Error: Username is already taken!") {
          error();
        }
        else if (response.message === "Error: Email is already in use!") {
          error1();
        }
        else {
          alert("Please enter valid details:UserName should be between 5 to 20, Email should be valid, Password should be between 5 to 20 characters.");
        }
      })
  }


  handleChange(changeObject) {
    this.setState(changeObject);
  }

  render() {
    return (
      <>
        <Header></Header>
        <form>
          <div className="userlogin-container">
            <h2 className="user-panel">USER REGISTRATION</h2>
            <img src = {Admin} style={{width : 250,  height: 250, marginLeft: "auto", marginRight: "auto"}}></img>
            <hr></hr>
            <div className="userregisterform">
              <label><b>UserName</b></label>
              <input
                type="text"
                placeholder="Enter username"
                onChange={(e) => this.handleChange({ username: e.target.value })}
              />
              <p style={{ color: "red" }}>{this.state.usernameError}</p>
            </div>

            <div className="userregisterform">
              <label><b>Email</b></label>
              <input
                type="email"
                placeholder="Enter Email"
                onChange={(e) => this.handleChange({ email: e.target.value })}
              />
              <p style={{ color: "red" }}>{this.state.emailError}</p>
            </div>

            <div className="userregisterform">
              <label><b>Password</b></label>
              <input
                type="password"
                placeholder="Enter password"
                onChange={(e) => this.handleChange({ password: e.target.value })}
              />
              <p style={{ color: "red" }}>{this.state.passwordError}</p>
            </div>

            <div className="userregisterform">
              <Link 
                type="submit"
                className="btn btn-success register-1"
                to="/search"
                onClick={(e) => { this.register(e); }}
              >
                Register
              </Link>
              <ToastContainer />
            </div>
            <p className="text-center">
              Have an account? <Link to="/userlogin">Login Here</Link>
            </p>
          </div>
        </form>
        <br></br><br></br><br></br><br></br><br></br>
      </>
    );
  }
}
