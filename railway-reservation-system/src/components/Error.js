import React from 'react'
import Header from './UserHeader'

const Error = () => {
  return (
    <>
    <Header/>
    <div
      style={{
        height: '100vh',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center'
      }}
    >
      <h1>Thank you for choosing Indian Railway.</h1>
    </div>
    </>
  )
}

export default Error
