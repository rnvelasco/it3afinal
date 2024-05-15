import React, {Component} from 'react';
import {Navbar, NavbarBrand} from 'reactstrap';
import {Link} from 'react-router-dom';

export default class AppNavbar extends Component {
    constructor(props) {
        super(props);
        this.state = {isOpen: false};
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
        return <div style={{position: 'relative'}}>
                    <Navbar color="dark" dark expand="md">
                        <NavbarBrand tag={Link} to="/" style={{marginLeft: 20}}>Home</NavbarBrand>
                        <NavbarBrand className="ms-auto"  style={{marginRight: 20}}>Final Examination</NavbarBrand>
                    </Navbar>
                </div>
    }
}