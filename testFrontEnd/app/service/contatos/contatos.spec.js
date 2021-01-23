describe('Testes', function () {
    var ContatosTest;
    
    beforeEach(angular.mock.module('api.contatos'));
    
    beforeEach(inject(function(Contatos) {
        ContatosTest = Contatos;
    }));

    it('Service funcionando', function () {
        expect(ContatosTest).toBeDefined();
    });
})