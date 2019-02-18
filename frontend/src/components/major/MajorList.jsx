import React, { Component } from 'react';
import Major from './Major';
import { Divider } from '@material-ui/core';

const URL = 'http://localhost:8080/majors';
class MajorList extends Component {
    state = {
        majors:[]
    }

    componentWillMount(){
        fetch(URL, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(response => {
            this.setState({...this.state, majors: response.data.content});
        })
    }

    render(){
        return (
            <div>
                {this.state.majors.map(major => (<Major major={major} />))}
            </div>
        );
    }
}

export default MajorList;