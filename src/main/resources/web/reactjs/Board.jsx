/**
 * Questa classe rappresenta la lavagna sullo schermo.
 * La lavagna può visualizzare più partite e:
 * - Aggiungere partite
 * - Rimuovere partite 
 */
class Board extends React.Component {
    constructor(props){
        super(props);
        this.state = {
          games: [],
          name: ""
        };
    }
    
   /**
    * Funzione richiamata quando l'untende
    * inserisce un nome nel campo di testo.
    * Qui mi salvo il nome digitato dall'utente
    * per inviarlo poi al backend
    * @param {string} event
    */
    setName(event){
        this.setState({name: event.target.value});
    }

   /**
    * Questa funzione chiede al backend di
    * preparare una nuova partita
    */
    aggiungiGiocatore(){
        if(this.state.name == ""){
            MarcoUtils.showNotification({
                title: "Hey",
                message: "Manca il nome"
            });
            return;
        } 
        
        
      let scope = this;
      MarcoUtils.executeAjax({
          url: __URLS.BOWLING.AGGIUNGI_PARTITA,
          dataToPost:{
              user: this.state.name
          },
          showLoading: true
      }).then(function(resp){
          if(resp.status){
             /**
              * se la partita è stata creata allora
              * la aggiungo alla lista delle partite
              */
              let games = scope.state.games.slice(); 
              games.push(<Game gameProps={resp.userGame} key={resp.userGame.user} eliminaPartita={scope.eliminaPartita.bind(scope)}/>);
              scope.setState({
                  games: games,
                  name: ""
              });
          }
      });
  }
    
   /**
    * Questa funzione è usata per eliminare
    * la partita dell'utente inserito
    * nel campo di testo.
    */
    eliminaGiocatore(){
        if(this.state.name == ""){
            MarcoUtils.showNotification({
                title: "Hey",
                message: "Manca il nome"
            });
            return;
        } 
        this.eliminaPartita(this.state.name);
    }
    
   /**
    * Rimuovo la parita che fa riferimento al bottone
    * "rimuovi" in ogni "partita"
    * @param {string} user
    */
    eliminaPartita(user){
        let scope = this;
        MarcoUtils.executeAjax({
            url: __URLS.BOWLING.RIMUOVI_PARTITA,
            dataToPost:{
                user: user
            },
            showLoading: true
        }).then(function(resp){
            if(resp.status){
                let games = scope.state.games.slice();
                let newGames = [];
                for(let i = 0; i < games.length; i++){
                    if(games[i].key == user){
                        continue;
                    }
                    newGames.push(games[i]);
                }
                scope.setState({
                    games: newGames,
                    name: ""
                });
                MarcoUtils.showNotification({
                    title: "Fatto",
                    message: "Partita eliminata",
                    type: __NOTIFY_TYPE.SUCCESS
                });
            }
        });
    }
    
    //funzione di React JS
    componentWillReceiveProps(props) {
        this.setState(props.frameProps);
    }
    
    //funzione di React JS
    componentDidMount(){
        MarcoUtils.initializeTooltips();
    }
    
    //funzione di React JS
    componentDidUpdate(){
        MarcoUtils.initializeTooltips();
    }
    
  render() {
    /*
    * La lavagna è composta da:
    * - campo di testo per inserire il nome del giocatore
    * - il bottone aggiungi partita
    * - il bottone elimina partita
    * - elenco delle partite
    */
    return (
        <React.Fragment>
            <div className="row">&nbsp;</div>
            <div className="row">
                <div class="col-8 col-sm-6 col-md-4 col-lg-3 col-xl-3">
                    <input type="text" 
                        className="form-control" 
                        onChange={this.setName.bind(this)} 
                        placeholder="Inserisci il nome" value={this.state.name}/>
                </div>
                <div class="col-4">
                    <button 
                        className="btn btn-outline-info" 
                        onClick={this.aggiungiGiocatore.bind(this)}
                        data-toggle="tooltip"
                        title="Aggiungi giocatore"
                        >
                        <i class="fa fa-plus" aria-hidden="true"></i>
                    </button>
                    &nbsp;
                    <button 
                        className="btn btn-outline-danger" 
                        onClick={this.eliminaGiocatore.bind(this)}
                        data-toggle="tooltip"
                        title="Elimina la partita di..."
                        >
                        <i class="fa fa-trash-o" aria-hidden="true"></i>
                    </button>
                </div>
            </div>
            {this.state.games}
        </React.Fragment>
    );
  }
}
