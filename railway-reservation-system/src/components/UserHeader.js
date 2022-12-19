import { Button } from 'bootstrap'
import React from 'react'
import { Link } from 'react-router-dom'
import authService from '../Services/auth.service'


const Header = () => {
  return (

    <header className='header'>
      <div>
        <Link className='links' to='/usersearch'>
          Indian Railway
        </Link>
      </div>
      <nav className='navbar'>
        <ul>
          <Link className='links' to='/usersearch'>
            Home
          </Link>

          <Link className='links' to='/usersearch'>
            Search
          </Link>
          
          <button style = {{backgroundColor: 'black', color: 'white', border:'none', marginRight:50, marginLeft:0}}  onClick= {() => authService.logout()}>
            Logout
          </button>
     

        </ul>
      </nav>
    </header>
  )
}

export default Header
