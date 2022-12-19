import "bootstrap/dist/css/bootstrap.min.css";
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { Button, Modal } from "react-bootstrap";
import AddTrain from "./components/AddTrain";
import SearchService from "./Services/SearchService";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import AdminHeader from "./components/AdminHeader"
import "./index.css"
import Kashmir from "./assets/Kashmir.jpg"
import  history from "./history"

toast.configure();

const TrainList = () => {
  /**To Show Add Toastify Text */
  const notify = () => {
    toast.success("Train Updated Successfully", {
      position: "top-center",
      autoClose: 3000,
    });
  };
  /**To Show Delete Toastify Text */
  const showdelete = () => {
    toast.error("Train Deleted", {
      position: "top-center",
      autoClose: 3000,
    });
  };

  const [Trains, setTrains] = useState([]);
  useEffect(() => {
    getAllTrains();
  }, []);

  const getAllTrains = () => {
    SearchService.getAllTrains()
      .then((response) => {
        setTrains(response.data);
        console.log(response.date);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const deleteTrain = (trainNo) => {
    SearchService.deleteTrain(trainNo)
      .then((response) => {
        getAllTrains();
      })
      .catch((error) => {
        console.log(error);
      });
  };

 

 
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  return (
    <div className="adminBoard">
    <AdminHeader/>
    <div class="container1">
    
      <div className="crud shadow-lg p-3 mb-5 mt-5 bg-body rounded">
        <div class="row ">
          <div
            class="col-sm-3 offset-sm-2 mt-5 mb-4 text-gred"
            style={{ color: "#D2691E" , marginLeft: 700}}
          >
            <h2>
              <b>TRAIN DETAILS</b>
            </h2>
          </div>
          <div class="col-sm-3 offset-sm-1  mt-5 mb-4 text-gred">
            <Link to="/addTrain">
              <Button variant="primary" onClick={handleShow}>
                Add Train
              </Button>
            </Link>
          </div>
        </div>
        <div class="row" >
          <div class="table-responsive ">
            <table class="table table-striped table-hover table-bordered">
              <thead>
                <tr>
                  <th>TrainNo </th>
                  <th>TrainName </th>
                  <th>SourceStation </th>
                  <th>DestinationStation </th>
                  <th>ArrivalTime </th>
                  <th>DepartureTime </th>
                  <th>Duration </th>
                  <th>NoOfSeats </th>
                  <th>FirstClass </th>
                  <th>SecondClass </th>
                  <th>ThirdClass </th>
                  <th>Sleeper </th>
                  <th>Actions </th>
                </tr>
              </thead>
              <tbody>
                {Trains.map((TrainDetails) => (
                  <tr key={TrainDetails.trainNo}>
                    <td> {TrainDetails.trainNo} </td>
                    <td> {TrainDetails.trainName} </td>
                    <td> {TrainDetails.sourceStation} </td>
                    <td>{TrainDetails.destinationStation}</td>
                    <td>{TrainDetails.arrivalTime}</td>
                    <td>{TrainDetails.deptTime}</td>
                    <td>{TrainDetails.duration}</td>
                    <td>{TrainDetails.noOfSeats}</td>
                    <td> {TrainDetails.firstClassACFare} </td>
                    <td> {TrainDetails.twoTierAcFare} </td>
                    <td> {TrainDetails.threeTierAcFare} </td>
                    <td> {TrainDetails.sleeperFare} </td>
                    <td>
                      <Link
                        className="btn btn-info"
                        to={`/edit-train/${TrainDetails.trainNo}`}
                      >
                        {" "}
                        Update{" "}
                      </Link>


                      <button
                        className="btn btn-danger"
                        onClick={() => {
                          deleteTrain(TrainDetails.trainNo);
                          showdelete();
                        }}
                        style={{ marginLeft: "2px", marginTop: "5px" }}
                      >
                        Delete{" "}
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>

        {/* <!--- Model Box ---> */}
        
      </div>
    </div>
    </div>
  );
};

export default TrainList;
