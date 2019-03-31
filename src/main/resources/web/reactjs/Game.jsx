/**
 * Questa classe si della gestione della singola partita.
 * - Lancio palla
 * - Eliminazione partita
 */
class Game extends React.Component{
    constructor(props){
        super(props);
        this.state = props.gameProps;
        this.state.birilliInPiedi = 10;
        this.state.primoLancioFrame = true;
    }
    
   /**
    * Il numero dei birilli abbattuti 
    * viene determinato "casualmente".
    */
    eseguiLancio(event){
        let birilliAbbattuti = event.target.value;
        let scope = this;
        
        MarcoUtils.executeAjax({
            url: __URLS.BOWLING.ESEGUI_LANCIO,
            dataToPost:{
                birilliAbbattuti: birilliAbbattuti ,
                user: this.state.user
            },
            showLoading: true
        }).then(function(resp){
            if(resp.status){ 
                //aggiorno lo stato
                let newState = resp.userGame;
                let birilliRimasti = scope.state.birilliInPiedi - birilliAbbattuti;
                
                newState.primoLancioFrame = !scope.state.primoLancioFrame;
                
                if(birilliRimasti == 0 || newState.primoLancioFrame){
                    birilliRimasti = 10;
                    newState.primoLancioFrame = true;
                }
                
                
                newState.birilliInPiedi = birilliRimasti;
                
                //imposto il nuovo stato
                scope.setState(newState);
            }
        });
    }
    
   /**
    * Elimina la partita del giocatore.
    * L'eliminazione viene delegata 
    * alla super classe Board
    */
    eliminaPartita(){
        this.props.eliminaPartita(this.state.user);
    }
    
    //Funzione standard di ReactJs
    componentWillReceiveProps(props) {
        this.setState(props.gameProps);
    }
    //Funzione standard di ReactJs
    componentDidUpdate(){
        MarcoUtils.initializeTooltips();
    }
    
    render(){
        /*
        * Preparo l'array di Frame da 
        * renderizzare
        */
        let framesToRender = [];
        for(let i = 0; i < this.state.frames.length; i++){
            framesToRender.push(<Frame frameProps={this.state.frames[i]} name={i} key={i}/>);
        }
        
        let bottoni = [];
        for(let i = 0; i < 11; i++){
            let disabled = i > this.state.birilliInPiedi;
            let btnClass = disabled ? "btn-outline-danger" : "btn-outline-info";
            bottoni.push(<button 
                    className={"btn " + btnClass + " bottone-lancio"} 
                    onClick={this.eseguiLancio.bind(this)}
                    data-toggle="tooltip"
                    title={"Abbatti " + i + " birilli"}
                    value={i}
                    disabled={disabled}
                >{i}</button>);
        }
        
        /*
        * La partita è composta da:
        * - una riga vuota (per avere un pò di spazio)
        * - una riga con per le azioni (Lancia palla e elimina parita)
        * - la riga che contiene i risultati (lista di Frame)
        */
        return(
            <React.Fragment>
                <div className="row">&nbsp;</div>
                <div className="barra-utente-partita">
                    <div className="info-utente">
                        <div className="badge-nome">{this.state.user}</div>
                    </div>
                    <div className="container-btn-elimina-partita">
                        <button 
                            className="btn btn-outline-danger" 
                            onClick={this.eliminaPartita.bind(this)}
                            data-toggle="tooltip"
                            title="Elimina partita"
                        ><i class="fa fa-trash-o" aria-hidden="true"></i></button>
                    </div>
                </div>
                <hr/>
                <div className="container-lanci">
                    {bottoni}
                </div>
                <hr/>
                <div className="bowling-game">
                    <div className="score-board">
                        {framesToRender}
                    </div>
                </div>
            </React.Fragment>
        );
    }
}