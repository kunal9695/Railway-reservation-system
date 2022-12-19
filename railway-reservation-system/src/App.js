 import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Header from "./components/Header";
import Showcase from "./components/Showcase";
import Footer from "./components/Footer";
import Login from "./components/Login.js";
import Destinations from "./components/Destinations";
import Error from "./components/Error";
import Search from "./components/Search";
import AddTrain from "./components/AddTrain";
import TrainList from "./TrainList";
import Booking from "./components/Booking";
import UserRegister from "./components/UserRegister";
import BookingList from "./components/BookingList";
import PaymentForm from "./components/PaymentForm";
import UserLogin from "./components/UserLogin";
import UserSearch from "./components/UserSearch"

function App() {
  return (
    <>
    <Router>
 
      <Switch>
        <Route exact path="/">
          <Showcase />
          <Destinations />
        </Route>

        <Route exact path="/login">
          <Login />
        </Route>

        <Route path="/userregistration">
          <UserRegister />
        </Route>

        <Route path="/userlogin">
          <UserLogin />
        </Route>

        <Route exact path="/adminTrainList">
           <TrainList/>
        </Route>

        <Route exact path="/trainList">
          <BookingList></BookingList>
        </Route>

        <Route exact path="/booking">
          <Booking></Booking>
        </Route>

       <Route exact path="/edit-train/:id">
          <AddTrain/>
        </Route>

        <Route exact path="/addTrain">
        <AddTrain/>
        </Route> 

        <Route exact path="/search">
          <Search />
        </Route>

        <Route exact path="/usersearch">
          <UserSearch />
        </Route>

        <Route path="/checkin">
          <Booking />
        </Route>

        <Route exact path="/payment">
          <PaymentForm></PaymentForm>
        </Route>

        <Route path="*">
          <Error />
        </Route>
      </Switch>
     
    </Router>
    <Footer />
    </>
  );
}

export default App;
