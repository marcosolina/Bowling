/**
 * Questa classe renderizza il singolo frame
 */
class Frame extends React.Component{
    constructor(props){
        super(props);
        this.state = props.frameProps;
    }
    
    //funzione di React JS
    componentWillReceiveProps(props) {
        this.setState(props.frameProps);
    }
    
    render(){
       /**
        * Un pò di logica per capire se devo mostrare la "X", la "/" o il numero
        */
        let simboloPrimoLancio = this.state.strike ? "" : this.state.lancio1;
        let simboloSecondoLancio = this.state.strike ? "X" : this.state.spare ? "/" : this.state.lancio2;
        let punteggioDelFrame = this.state.punteggioAQuestoFrame > -1 ? this.state.punteggioAQuestoFrame : "";
        let lancioBonus;

        /*
        * L'ultimo frame a un pò di logica personalizzata
        */
        if(this.props.name == 9){
            simboloPrimoLancio = this.state.lancio1 == 10 ? "X" : this.state.lancio1;
            simboloSecondoLancio = this.state.lancio2 == 10 ? "X" : this.state.spare ? "/" : this.state.lancio2; 
            lancioBonus = <div className="frame-terzo-lancio">{this.state.lancioBonus == 10 ? "X" : this.state.lancioBonus == 0 ? "" : this.state.lancioBonus}</div>;
        }
        
        return(
                <div className="frame-container">
                    <div className="frame-name">{this.props.name + 1}</div>
                    <div className="frame-container-lanci">
                        <div className="frame-primo-lancio">{simboloPrimoLancio}</div>
                        <div className="frame-secondo-lancio">{simboloSecondoLancio}</div>
                        {lancioBonus}
                    </div>
                    <div className="frame-punteggio">{punteggioDelFrame}</div>
                </div>
        );
    }
}