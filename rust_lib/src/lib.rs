#[no_mangle]
pub extern "C" fn process_image(data: *const u8, len: usize) -> i32 {
    0
}

#[no_mangle]
pub extern "C" fn match_template(haystack: *const u8, h_len: usize, needle: *const u8, n_len: usize) -> f32 {
    0.0
}