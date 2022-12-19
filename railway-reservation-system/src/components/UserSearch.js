import "bootstrap/dist/css/bootstrap.min.css";
import { Button, Modal } from "react-bootstrap";
import React, { useState, useEffect } from "react";
import { Link, Redirect } from "react-router-dom";
import SearchService from "../Services/SearchService";
import "./Search.css";
import Header from "./UserHeader";
import history from "../history";
import { Select } from "antd";


const UserSearch = () => {
    const [Trains, setTrains] = useState([]);
    const [sourceStation, setsourceStation] = useState("");
    const [destinationStation, setdestinationStation] = useState("");

    const searchTrain = (e) => {
        e.preventDefault();

        SearchService.getTrainsByRoute(sourceStation, destinationStation)
            .then((response) => {
                var e = JSON.stringify(response.data);
                console.log(response);
                setTrains(response.data);
            })
            .catch((error) => {
                console.log(error);
                alert("Train not available.")
            });
    };

    const direct = (TrainDetails) => {
        const user = localStorage.getItem('user');
        console.log(user)
        if (user) {

            localStorage.setItem('trainNo', TrainDetails.trainNo);
            localStorage.setItem('trainName', TrainDetails.trainName);
            localStorage.setItem('sourceStation', TrainDetails.sourceStation);
            localStorage.setItem('destinationStation', TrainDetails.destinationStation);
            history.push("/booking")
        }
        else {
            history.push("/userlogin")
        }
    }



    var count = 0;

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    return (
        <>
            <Header />
            <form>
                <div className="search-container">
                    <h1 className="searching">SEARCH TRAINS</h1>
                    <hr></hr>
                    <div className="form-searching">
                        <input
                            type="text"
                            name="sourceStation"
                            id="sourceStation"
                            placeholder="Enter Start Station"
                            value={sourceStation}
                            onChange={(e) => setsourceStation(e.target.value)}
                            required
                        ></input>
                        {/* <label>Choose sourceStation : </label>
                        <select id = "location">
                            <option id = "Delhi">Delhi</option>
                            <option id = "Lucknow">Lucknow</option>
                            <option id = "Kashmir">Kashmir</option>
                            <option id = "Kanyakumari">Kanyakumari</option>
                            <option id = "Mumbai">Mumbai</option>
                            <option id = "Bengaluru">Bengaluru</option>
                            <option id = "Chennai">Chennai</option>
                            <option id = "Kolkata">Kolkata</option>
                        </select> */}

                        <input
                            type="text"
                            placeholder="Enter End Station"
                            name="destinationStation"
                            id="destinationStation"
                            value={destinationStation}
                            onChange={(e) => setdestinationStation(e.target.value)}
                            required
                        ></input>
                        <hr></hr>
                        <button className="btn btn-success button1" onClick={(e) => searchTrain(e)}>
                            Search Trains
                        </button>
                    </div>
                </div>

                {/* <div class="container">
                    <div className="search search1 crud shadow-lg p-3 mb-5 mt-5 bg-body rounded col-sm-3 mt-5 mb-4 text-gred text-center">
                        <form class="form-inline">
                            <input
                                class="form-control mr-sm-2"
                                type="search"
                                placeholder="Search By PNR"
                                aria-label="Search"
                            />
                        </form>
                        <button className="btn btn-success mt-2">Search</button>
                    </div>
                </div> */}


                <div class="container ">
                    <div className="crud shadow-lg p-3 mb-5 mt-5 bg-body rounded">
                        <div class="row ">
                            <div
                                class="col-lg-8 offset-sm-2 mt-5 mb-4 "
                                style={{ color: "#D2691E", marginLeft: 450 }}
                            >
                                <h3>
                                    <b>LIST OF TRAINS AVAILABLE</b>
                                </h3>
                            </div>
                        </div>
                        <div class="row">
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
                                            <tr id={count++} key={TrainDetails.trainNo}>
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
                                                    {" "}

                                                    <button style={{ backgroundColor: 'dodgerblue', color: 'White', borderColor: 'white', marginLeft: 0 }} onClick={() => direct(TrainDetails)}>
                                                        Book
                                                    </button>

                                                </td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        {/* <!--- Model Box ---> */}
                        <div className="model_box">
                            <Modal
                                show={show}
                                onHide={handleClose}
                                backdrop="static"
                                keyboard={false}
                            >
                                <Modal.Header closeButton>
                                    <Modal.Title></Modal.Title>
                                </Modal.Header>
                                <Modal.Body></Modal.Body>

                                <Modal.Footer>
                                    <Button variant="secondary" onClick={handleClose}>
                                        Close
                                    </Button>
                                </Modal.Footer>
                            </Modal>

                            {/* Model Box Finish*/}
                        </div>
                    </div>
                </div>
            </form>
        </>
    );
};

export default UserSearch;
