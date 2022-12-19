import React, { useEffect, useState } from 'react'
import PaymentService from '../Services/PaymentService'
import { useHistory, useParams } from 'react-router-dom';
import { Link } from 'react-router-dom'
import Header from './UserHeader';



const PaymentForm = () => {

  const [pnrNo, setpnrNo] = useState('')
  const [bankName, setbankName] = useState('')
  const [cardNo, setcardNo] = useState('')
  const [cvv, setcvv] = useState('')
  const [classType, setclassType] = useState('')
  const history = useHistory();

  useEffect ( () => {
    const pnr = localStorage.getItem("pnrNo");
    setpnrNo(pnr);
  },[]);

  const makePayment = (e) => {
    e.preventDefault();
    const PaymentDetails = { pnrNo, bankName, cardNo, cvv, classType }

    PaymentService.makePayment(PaymentDetails).then((response) => {
      var e = JSON.stringify(response.data);
      alert(e);
      console.log(response);
      history.push('/paymentSucessful')
    }).catch(error => {
      console.log(error)
    })


  }
  return (
    <>
      <Header />
      <div>
        <br></br>
        <div className="container">
          <div className="row">
            <div className="card col-md-6 offset-md-3 offset-md-3">
              <br></br><br></br>
              <h2 className="text-center">Payment</h2>
              <div className="card-body">
                <form>
                  <div className="form-group mb-2">
                    <label className="form-label">PNR NO</label>

                    <input
                      type="text"
                      placeholder="Enter PNR No"
                      name="pnrNo"
                      className="form-control"
                      value={pnrNo}
                    //onChange={(e) => setpnrNo(e.target.value)}
                    >
                    </input>

                  </div>
                  <div className="form-group mb-2">
                    <label className="form-label">BankName</label>

                    <input
                      type="text"
                      placeholder="Enter Bank Name"
                      name="bankName"
                      className="form-control"
                      value={bankName}
                      onChange={(e) => setbankName(e.target.value)}>
                    </input>

                  </div>
                  <div className="form-group mb-2">
                    <label className="form-label">Card No</label>

                    <input
                      type="text"
                      placeholder="Enter card No"
                      name="cardNo"
                      className="form-control"
                      value={cardNo}
                      onChange={(e) => setcardNo(e.target.value)}>
                    </input>

                  </div>

                  <div className="form-group mb-2">
                    <label className="form-label">CVV</label>

                    <input
                      type="text"
                      placeholder="Enter cvv"
                      name="cvv"
                      className="form-control"
                      value={cvv}
                      onChange={(e) => setcvv(e.target.value)}>
                    </input>

                  </div>

                  <Link className="btn btn-success" onClick={(e) => makePayment(e)} >Pay</Link>
                  <button to="/login" className="btn btn-danger"> Cancel </button>
                </form>
              </div>

            </div>

          </div>
        </div>
        <br></br>
      </div>
    </>
  )

}
export default PaymentForm
