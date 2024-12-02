pub fn day1() {
    let _part1_result = day1_part1(false);
    println!("Day 1, Part 1: {}", _part1_result);
}

fn day1_part1(test: bool) -> i32 {
    let (mut first, mut second) = if test {
        parse_input(TEST_INPUT)
    } else {
        parse_input(INPUT)
    };
    first.sort();
    second.sort();
    first
        .iter()
        .zip(second.iter())
        .map(|(a, b)| (a - b).abs())
        .sum()
}

fn parse_input(input: &str) -> (Vec<i32>, Vec<i32>) {
    input
        .lines()
        .map(|line| {
            let splitted = line
                .split_whitespace()
                .map(|x| x.parse::<i32>().unwrap())
                .collect::<Vec<i32>>();
            (splitted[0], splitted[1])
        })
        .into_iter()
        .unzip()
}

const TEST_INPUT: &str = include_str!("test_input.txt");
const INPUT: &str = include_str!("input.txt");
