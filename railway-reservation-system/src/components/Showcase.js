import React from 'react'
import Header from './Header';


function Showcase() {
  return (
    <>
    <Header/>
    <div className='hero-container'>
  
      <video src='/videos/Railway1.mp4' autoPlay loop muted />
      {/* <div className='hero-btns'>
        <Button
          className='btns'
          buttonStyle='btn--outline'
          buttonSize='btn--large'
        >
          GET STARTED
        </Button>
      </div> */}
    </div>
    </>
  );
}

export default Showcase;

