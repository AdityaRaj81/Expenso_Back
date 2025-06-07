@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(userService.loginUser(loginDTO));
    }
}
