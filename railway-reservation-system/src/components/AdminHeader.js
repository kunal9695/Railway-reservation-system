import React from 'react'
import { Link } from 'react-router-dom'
import './Login.css'
import authService from '../Services/auth.service'
import { Button } from 'bootstrap'

const AdminHeader = () => {
  return (
    <header className='header'>
      <div>
        <Link className='links' to='/adminTrainList'>
          Indian Railway
        </Link>
      </div>
      <nav className='navbar'>
        <ul>
          <Link className='links' to='/adminTrainList'>
            Home
          </Link>
          <button style = {{backgroundColor: 'black', color: 'white', border:'none', marginRight:50, marginLeft:0}}  onClick= {() => authService.logout()}>
            Logout
          </button>
        </ul>
      </nav>
    </header>
  )
}

export default AdminHeader
