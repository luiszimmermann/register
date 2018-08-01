package register

class Billing {
    Integer id
    String name
    String email
    String cpf
    Date dueDate
    Double value
    String paymentMethod
    String description
    Boolean deleted
    
    static mapping = {
        table "billings"
        id column: 'id', type: 'integer'
    }

    static constraints = {
        name nullable: false, blank: false
        email email: true, blank: true, nullable: true
        cpf blank: true, nullable: true
        value min: 5.0d, nullable: false, blank: false
        paymentMethod inList: ['Boleto Bancário', 'Cartão de Crédito'], nullable: false, blank: false
        dueDate min: new Date(), nullable: false, blank: false
        description nullable: false, blank: false
    }

    static namedQueries = {
        nameOrEmailStartsWith { nameEmail ->
            or {
                like 'name', '${nameEmail}%'
                like 'email', '${nameEmail}%'
            }
        }

        paymentMethodIs { pMethod ->
            eq 'paymentMethod', pMethod
        }

        descriptionStartsWith { description ->
            like 'description', '${description}%'
        }
    }
}