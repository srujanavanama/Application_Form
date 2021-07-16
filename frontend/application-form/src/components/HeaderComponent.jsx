import React from 'react';
import { Link, withRouter } from 'react-router-dom';

const HeaderComponent = () => {
    return (
        <header>
            <nav className="navbar navbar-dark bg-primary">
                <ul className="navbar-nav mr-auto">
                    <li className="nav-item active">
                        <Link className="nav-link" to="/">Home</Link>
                    </li>
                </ul>
                <ul className="navbar-nav justify-content-end">
                    <li className="nav-item active">
                        <Link className="nav-link" to="/new">New Application</Link>
                    </li>
                </ul>
            </nav>
        </header>
    )
}

export default withRouter(HeaderComponent)