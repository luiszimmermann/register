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
    Boolean deleted = false
    CpfService cpfService

    static transients = ['cpfService']

    static mapping = {
        autowire true
        table "billings"
        id column: 'id', type: 'integer'
        deleted defaultValue: false
    }

    static constraints = {
        name nullable: false, blank: false
        email email: true, blank: true, nullable: true
        cpf blank: true, nullable: true, validator: { value, obj, errors ->  if (value && !(obj.cpfService.validate(value))) errors.rejectValue("cpf", "billing.cpf.invalid") }
        value min: 5.0d, scale: 2, nullable: false, blank: false
        paymentMethod inList: ['Boleto Bancário', 'Cartão de Crédito'], nullable: false, blank: false
        dueDate min: new Date(), nullable: false, blank: false
        description nullable: false, blank: false
    }

    static namedQueries = {
        nameOrEmailStartsWith { nameEmail ->
            or {
                like 'name', nameEmail+'%'
                like 'email', nameEmail+'%'
            }
        }

        paymentMethodIs { pMethod ->
            eq 'paymentMethod', pMethod
        }

        dueDateBetween { Date begin, Date end ->
            between 'dueDate', begin, end
        }

        descriptionStartsWith { desc ->
            like 'description', desc+'%'
        }
    }
}