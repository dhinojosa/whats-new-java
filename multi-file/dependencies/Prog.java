class Prog {
    public static void main(String[] args) {
        String data = """
            {
                "name": "John",
                "age": 30,
                "city": "New York"
            }
            """;
        var result = Parser.parse(data);
        System.out.println(result);
    }
}
