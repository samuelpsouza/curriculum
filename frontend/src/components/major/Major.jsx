import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import { ExpansionPanel, ExpansionPanelSummary,
  ExpansionPanelDetails, Button, Typography } from '@material-ui/core';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import FullView from './FullView';

const styles = theme => ({
    root: {
      ...theme.mixins.gutters(),
      paddingTop: theme.spacing.unit * 2,
      paddingBottom: theme.spacing.unit * 2,
    },
    button: {
      margin: theme.spacing.unit,
    },
});

class Major extends Component {
    constructor(props){
      super(props);

      this.state = {
        open: false,
      };
    }
    

    handleClickOpen = () => {
      this.setState({ open: true });
    };

    handleClose = () => {
      this.setState({ open: false });
    };

    render(){
      const { classes, major, fullScreen } = this.props;
      return (
        <ExpansionPanel>
          <ExpansionPanelSummary expandIcon={<ExpandMoreIcon />}>
            <Typography variant="h5" component="h3">
              {major.title}
            </Typography>
          </ExpansionPanelSummary>
          <ExpansionPanelDetails>
            <Typography component="p">
              {major.description}
            </Typography>
            <Button color="primary" className={classes.button} onClick={() => this.handleClickOpen()}>
              Visualizar
            </Button>
          </ExpansionPanelDetails>
          <FullView fullScreen={fullScreen} open={this.state.open} handleClose={this.handleClose} major={major}/>
        </ExpansionPanel>
      )
    }
}

export default withStyles(styles)(Major);