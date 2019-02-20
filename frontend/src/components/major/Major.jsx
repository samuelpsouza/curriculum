import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import { ExpansionPanel, ExpansionPanelSummary,
  ExpansionPanelDetails, Button, Typography, Divider, 
  ExpansionPanelActions } from '@material-ui/core';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import FullView from './FullView';
import MatrixForm from '../matrix/MatrixForm';

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
        openInclude: false,
      };
    }

    handleClickOpen = () => {
      this.setState({ ...this.state, open: true });
    };

    handleClickOpenIncludeForm = () => {
      this.setState({ ...this.state, openInclude: true });
    }

    handleClose = () => {
      this.setState({ ...this.state, open: false });
    };

    handleClickCloseIncludeForm = () => {
      this.setState({ ...this.state, openInclude: false });
    }

    render(){
      const { classes, major, fullScreen, courses, handleSubmit } = this.props;
      
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
          </ExpansionPanelDetails>
          <Divider />
          <ExpansionPanelActions>
            <Button color="secondary" className={classes.button} onClick={() => this.props.handleRemove(major.id)}>
                Remover
            </Button>
            <Button className={classes.button} onClick={() => this.handleClickOpen()}>
                Editar
            </Button>
            <Button className={classes.button} onClick={() => this.handleClickOpenIncludeForm()}>
                Incluir
            </Button>
            <Button color="primary" className={classes.button} onClick={() => this.handleClickOpen()}>
                Visualizar
            </Button>
          </ExpansionPanelActions>
          <FullView fullScreen={fullScreen} open={this.state.open} handleClose={this.handleClose} major={major}/>
          <MatrixForm major={major} courses={courses} openInclude={this.state.openInclude} handleClose={this.handleClickCloseIncludeForm} handleSubmit={handleSubmit} />
        </ExpansionPanel>
      )
    }
}

export default withStyles(styles)(Major);