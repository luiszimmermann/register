package register

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class BillingController {

    BillingService billingService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", search: "GET"]

    def search() {
        respond Billing.nameOrEmailStartsWith(params.name).descriptionStartsWith(params.desc).dueDateBetween(params.dueDateBegin, params.dueDateEnd).paymentMethodIs(params.paymentMethod).list(), view: 'index', model:[billing: new Billing()]
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond billingService.list(params), model:[billing: new Billing()]
    }

    def show(Long id) {
        respond billingService.get(id)
    }

    def create() {
        respond new Billing(params)
    }

    def save(Billing billing) {
        if (billing == null) {
            notFound()
            return
        }

        try {
            billingService.save(billing)
        } catch (ValidationException e) {
            respond billing.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'billing.label', default: 'Billing'), billing.id])
                redirect billing
            }
            '*' { respond billing, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond billingService.get(id)
    }

    def update(Billing billing) {
        if (billing == null) {
            notFound()
            return
        }

        try {
            billingService.save(billing)
        } catch (ValidationException e) {
            respond billing.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'billing.label', default: 'Billing'), billing.id])
                redirect billing
            }
            '*'{ respond billing, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        Billing billing = billingService.get(id)
        billing.deleted = true
        billingService.save(billing)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'billing.label', default: 'Billing'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'billing.label', default: 'Billing'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
