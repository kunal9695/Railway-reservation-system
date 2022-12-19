import "bootstrap/dist/css/bootstrap.min.css";
import React, { Component, useContext, useState, Fragment } from "react";
import { Formik, Form, Field } from "formik";
import { useHistory, useLocation } from "react-router-dom";
import * as Yup from "yup";
import _get from "lodash.get";
import "./Login.css";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Header from "./Header";
import Admin from "../assets/Admin.png"
import AuthService from "../Services/auth.service";
import Kashmir from "../assets/Kashmir.jpg"
import Tooltip from "react-bootstrap/Tooltip"
import OverlayTrigger from "react-bootstrap/OverlayTrigger"


toast.configure()

const LoginSchema = Yup.object().shape({
  password: Yup.string().required("Password is required!"),
  username: Yup.string().required("username is required!"),
});

const Login = () => {

  /**To Show Add Toastify Text */
  const notify = () => {
    toast.success("Admin LoggedIn Successfully!!!", {
      position: "top-center",
      autoClose: 2000,
    });
  };

  /**To Show Error Toastify Text */
  const error = () => {
    toast.error("Invalid Credentials!!!", {
      position: "top-center",
      autoClose: 2000,
    });
  };

  
  const history = useHistory();

  const login = (userData) => {
    AuthService.login(userData.username, userData.password)
      .then(() => {
        history.push("/adminTrainList");
        notify();
        window.location.reload();
      }).catch((err) => {
        console.log(err);
        error();
        //alert("Invalid Credentials")
      })


  }

  return (
    <>
      <Header />

      <Formik
        initialValues={{
          username: "",
          password: "",
        }}
        validationSchema={LoginSchema}
        onSubmit={async (values, { resetForm }) => {
          try {
            const userData = { ...values };
            resetForm();
            // notify();
            login(userData);
          } catch (err) {
            // error();
            console.error(err);
          }
        }}
      >
        {() => (
          <Form>
            <div className="booking-container">
              <h1 className="booking">ADMIN LOGIN</h1>
              <img src = {Admin} style={{width : 250,  height: 250, marginLeft: "auto", marginRight: "auto"}}></img>
              <hr></hr>
              <div className="inner">
                <label ><b>User Name</b></label>
                <Field name="username" type="text" placeholder="Enter username"  />
                <label><b>Password</b></label>
                <Field name="password" type="password" placeholder="Password" />
                <button
                  className="btn btn-success" 
                  type="submit"
                  onClick={() => { }}
                >
                  Sign In
                </button>
              </div>
            </div>
          </Form>

        )}
      </Formik>
    </>
  );
};
export default Login;